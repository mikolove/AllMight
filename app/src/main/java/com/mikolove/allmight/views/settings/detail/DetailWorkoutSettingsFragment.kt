package com.mikolove.allmight.views.settings.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikolove.allmight.R
import com.mikolove.allmight.adapters.BasicInfoAdapter
import com.mikolove.allmight.adapters.BasicInfoViewHolder
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentDetailsWorkoutSettingBinding
import timber.log.Timber

class DetailWorkoutSettingsFragment : Fragment(){

    private lateinit var binding : FragmentDetailsWorkoutSettingBinding
    private lateinit var viewModel : DetailWorkoutSettingsViewModel
    private var workoutId : Int = 0
    private var status : Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_workout_setting, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val arguments = DetailWorkoutSettingsFragmentArgs.fromBundle(arguments!!)

        workoutId = arguments.workoutId
        status = arguments.status

        val viewModelFactory = DetailWorkoutSettingsViewModelFactory(
            workoutId,
            getString(R.string.default_workout),
            status,
            dataSource,
            application)

        setHasOptionsMenu(true)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val adapter = BasicInfoAdapter()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailWorkoutSettingsViewModel::class.java)

        binding.detailWorkoutSettingsViewModel = viewModel

        binding.detailWorkoutSettingsRecyclerViewExercise.layoutManager = linearLayoutManager
        binding.detailWorkoutSettingsRecyclerViewExercise.adapter = adapter

        binding.lifecycleOwner = this

        viewModel.exercises.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            viewModel.showAndHide(it.size)

        })
        viewModel.workout.observe(viewLifecycleOwner, Observer {
            viewModel.loadWorkoutType()
        })

        viewModel.getListWorkoutType().observe(viewLifecycleOwner, Observer {
            viewModel.loadWorkoutType()
        })

        viewModel.getWorkoutType().observe(viewLifecycleOwner, Observer {
            it?.let{
                viewModel.updateWorkoutType()
            }
        })

        viewModel.navigateToHomeSettings.observe(viewLifecycleOwner, Observer {
            it?.let{
                    this.findNavController().navigateUp()
                    viewModel.doneNavigatingToHomeSettings()
            }
        })

        viewModel.navigateToAdd.observe(viewLifecycleOwner, Observer { it ->
            it?.let{
                        this.findNavController().navigate(DetailWorkoutSettingsFragmentDirections.actionDetailWorkoutSettingsFragmentToWorkoutAddExercisesFragment().setWorkoutId(workoutId))
                        viewModel.doneNavigatingToAdd()
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        if(workoutId > 0)
            menu.findItem(R.id.detail_action_delete).isVisible = true

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.detail_action_done -> {

                if(viewModel.workout.value?.name.isNullOrEmpty()) {
                    Toast.makeText(context, R.string.error_name_empty, Toast.LENGTH_SHORT).show()
                }else{
                    if(viewModel.workout.value?.id!! > 0){
                        viewModel.updateWorkout()
                    }else{
                        viewModel.insertWorkout()
                    }
                }
                true
            }
            R.id.detail_action_delete -> {
                viewModel.deleteWorkout()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}
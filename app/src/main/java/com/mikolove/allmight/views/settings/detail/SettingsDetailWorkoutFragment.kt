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
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentSettingsDetailsWorkoutBinding

class SettingsDetailWorkoutFragment : Fragment(){

    private lateinit var binding : FragmentSettingsDetailsWorkoutBinding
    private lateinit var detailWorkoutViewModel : SettingsDetailWorkoutViewModel
    private var workoutId : Int = 0
    private var status : Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings_details_workout, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val arguments = SettingsDetailWorkoutFragmentArgs.fromBundle(arguments!!)

        workoutId = arguments.workoutId
        status = arguments.status

        val viewModelFactory = SettingsDetailWorkoutViewModelFactory(
            workoutId,
            getString(R.string.default_workout),
            status,
            dataSource,
            application)

        setHasOptionsMenu(true)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val adapter = BasicInfoAdapter()

        detailWorkoutViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsDetailWorkoutViewModel::class.java)

        binding.detailWorkoutSettingsViewModel = detailWorkoutViewModel

        binding.detailWorkoutSettingsRecyclerViewExercise.layoutManager = linearLayoutManager
        binding.detailWorkoutSettingsRecyclerViewExercise.adapter = adapter

        binding.lifecycleOwner = this

        detailWorkoutViewModel.workoutWithExercise.observe(viewLifecycleOwner, Observer {
            detailWorkoutViewModel.loadWorkoutType()
            adapter.submitList(it.exercises)
            detailWorkoutViewModel.showAndHide(it.exercises.size)
        })

        detailWorkoutViewModel.getListWorkoutType().observe(viewLifecycleOwner, Observer {
            detailWorkoutViewModel.loadWorkoutType()
        })

        detailWorkoutViewModel.getSpinnerWorkoutType().observe(viewLifecycleOwner, Observer {
            it?.let{
                detailWorkoutViewModel.updateWorkoutType()
            }
        })

        detailWorkoutViewModel.navigateToHomeSettings.observe(viewLifecycleOwner, Observer {
            it?.let{
                this.findNavController().navigateUp()
                detailWorkoutViewModel.doneNavigatingToHomeSettings()
            }
        })

        detailWorkoutViewModel.navigateToAdd.observe(viewLifecycleOwner, Observer { it ->
            it?.let{
                this.findNavController().navigate(SettingsDetailWorkoutFragmentDirections.actionSettingsDetailWorkoutFragmentToAddExercisesFragment().setWorkoutId(workoutId))
                detailWorkoutViewModel.doneNavigatingToAdd()
            }
        })

        //Lazy deal
        if(workoutId == 0){
            binding.detailWorkoutSettingsBtnAdd.isClickable = false
            binding.detailWorkoutSettingsBtnAdd.isEnabled = false
        }

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

                if(detailWorkoutViewModel.getWorkout()?.name.isNullOrEmpty()) {
                    Toast.makeText(context, R.string.error_name_empty, Toast.LENGTH_SHORT).show()
                }else{
                    if(detailWorkoutViewModel.getWorkout()?.id_workout!! > 0){
                        detailWorkoutViewModel.updateWorkout()
                    }else{
                        detailWorkoutViewModel.insertWorkout()
                    }
                }
                true
            }
            R.id.detail_action_delete -> {
                detailWorkoutViewModel.deleteWorkout()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}
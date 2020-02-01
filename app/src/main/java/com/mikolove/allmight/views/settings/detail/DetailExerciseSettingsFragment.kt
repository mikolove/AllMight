package com.mikolove.allmight.views.settings.detail

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentDetailsExerciseSettingBinding

class DetailExerciseSettingsFragment : Fragment(){

    private lateinit var binding : FragmentDetailsExerciseSettingBinding
    private lateinit var viewModel : DetailExerciseSettingsViewModel
    private var exerciseId : Int = 0
    private var status : Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_exercise_setting, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val arguments = DetailExerciseSettingsFragmentArgs.fromBundle(arguments!!)

        exerciseId = arguments.exerciseId
        status = arguments.status

        val viewModelFactory = DetailExerciseSettingsViewModelFactory(exerciseId,status, dataSource, application)

        setHasOptionsMenu(true)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailExerciseSettingsViewModel::class.java)

        binding.detailExerciseSettingsViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.exercise.observe(viewLifecycleOwner, Observer {
            viewModel.loadWorkoutType()
            viewModel.loadRep()
            viewModel.loadSeries()
        })

        viewModel.getListWorkoutType().observe(viewLifecycleOwner, Observer {
            viewModel.loadWorkoutType()
        })

        viewModel.getRepValue().observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.updateRep()
            }
        })

        viewModel.getSeriesValue().observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.updateSerie()
            }
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

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        if(exerciseId > 0)
            menu.findItem(R.id.detail_action_delete).isVisible = true

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.detail_action_done -> {
                if(viewModel.exercise.value?.id!! > 0){
                    viewModel.updateExercise()
                }else{
                    viewModel.insertExercise()
                }
                true
            }
            R.id.detail_action_delete -> {
                viewModel.deleteExercise()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}
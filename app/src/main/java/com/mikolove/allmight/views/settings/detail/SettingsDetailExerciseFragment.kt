package com.mikolove.allmight.views.settings.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentSettingsDetailsExerciseBinding

class SettingsDetailExerciseFragment : Fragment(){

    private lateinit var binding : FragmentSettingsDetailsExerciseBinding
    private lateinit var detailExerciseViewModel : SettingsDetailExerciseViewModel
    private var exerciseId : Int = 0
    private var status : Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings_details_exercise, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val arguments = SettingsDetailExerciseFragmentArgs.fromBundle(arguments!!)

        exerciseId = arguments.exerciseId
        status = arguments.status

        val viewModelFactory = SettingsDetailExerciseViewModelFactory(
            exerciseId,
            getString(R.string.default_workout),
            status,
            dataSource,
            application)

        setHasOptionsMenu(true)

        detailExerciseViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsDetailExerciseViewModel::class.java)

        binding.detailExerciseSettingsViewModel = detailExerciseViewModel
        binding.lifecycleOwner = this

        detailExerciseViewModel.exercise.observe(viewLifecycleOwner, Observer {
            detailExerciseViewModel.loadWorkoutType()
            detailExerciseViewModel.loadRep()
            detailExerciseViewModel.loadSeries()
        })

        detailExerciseViewModel.getListWorkoutType().observe(viewLifecycleOwner, Observer {
            detailExerciseViewModel.loadWorkoutType()
        })

        detailExerciseViewModel.getRepValue().observe(viewLifecycleOwner, Observer {
            it?.let {
                detailExerciseViewModel.updateRep()
            }
        })

        detailExerciseViewModel.getSeriesValue().observe(viewLifecycleOwner, Observer {
            it?.let {
                detailExerciseViewModel.updateSerie()
            }
        })

        detailExerciseViewModel.getWorkoutType().observe(viewLifecycleOwner, Observer {
            it?.let{
                detailExerciseViewModel.updateWorkoutType()
            }
        })

        detailExerciseViewModel.navigateToHomeSettings.observe(viewLifecycleOwner, Observer {
            it?.let{
                    this.findNavController().navigateUp()
                    detailExerciseViewModel.doneNavigatingToHomeSettings()
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

                if(detailExerciseViewModel.exercise.value?.name.isNullOrEmpty()) {
                    Toast.makeText(context, R.string.error_name_empty, Toast.LENGTH_SHORT).show()
                }else{
                    if(detailExerciseViewModel.exercise.value?.id!! > 0){
                        detailExerciseViewModel.updateExercise()
                    }else{
                        detailExerciseViewModel.insertExercise()
                    }
                }
                true
            }
            R.id.detail_action_delete -> {
                detailExerciseViewModel.deleteExercise()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}
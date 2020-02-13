package com.mikolove.allmight.views.settings.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.databinding.FragmentSettingsExerciseBinding
import com.mikolove.allmight.views.settings.SettingsFragmentDirections

class SettingsExerciseFragment : Fragment() {

    private lateinit var exerciseViewModel : SettingsExerciseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentSettingsExerciseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings_exercise, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = SettingsExerciseViewModelFactory(dataSource, application)

        exerciseViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsExerciseViewModel::class.java)

        binding.exerciseSettingsViewModel = exerciseViewModel

        binding.lifecycleOwner = this
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = ExerciseSettingsAdapter(
            SettingsExerciseListener { view: View, exercise: Exercise ->
                when(view.id){
                    R.id.list_item_exercise_title -> {
                        val direction = SettingsFragmentDirections.actionSettingsFragmentToSettingsDetailExerciseFragment().setExerciseId(exercise.id).setStatus(exercise.status)
                        findNavController().navigate(direction)
                    }
                    R.id.list_item_exercise_ic_delete -> {
                        exerciseViewModel.deleteExercise(exercise.id)
                    }
                }
            }
        )

        binding.exerciseSettingsRecyclerView.layoutManager = linearLayoutManager
        binding.exerciseSettingsRecyclerView.adapter = adapter

        binding.exerciseSettingsChipGroup.setOnCheckedChangeListener{ group, checked ->
            when(checked) {
                R.id.exercise_settings_chip_active -> {
                    exerciseViewModel.setFilterStatus(true)
                    exerciseViewModel.onFilterChange()
                }
                R.id.exercise_settings_chip_inactive -> {
                    exerciseViewModel.setFilterStatus(false)
                    exerciseViewModel.onFilterChange()
                }
            }
        }

        exerciseViewModel.exercises?.observe( viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                exerciseViewModel.showAndHide(it.size)
            }
        })

        exerciseViewModel.getFilterStatus().observe(viewLifecycleOwner, Observer {
            exerciseViewModel.onFilterChange()
        })
        exerciseViewModel.getFilterWkType().observe(viewLifecycleOwner, Observer {
            exerciseViewModel.onFilterChange()
        })


        return binding.root
    }
}
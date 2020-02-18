package com.mikolove.allmight.views.settings.workout

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
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.databinding.FragmentSettingsWorkoutBinding
import com.mikolove.allmight.views.settings.SettingsFragmentDirections

class SettingsWorkoutFragment : Fragment() {

    private lateinit var workoutViewModel : SettingsWorkoutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentSettingsWorkoutBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_settings_workout, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = SettingsWorkoutViewModelFactory(dataSource, application)

        workoutViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsWorkoutViewModel::class.java)

        binding.workoutSettingsViewModel = workoutViewModel

        binding.lifecycleOwner = this
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = WorkoutSettingsAdapter(
            SettingsWorkoutListener { view: View, workout: Workout ->
                when(view.id){
                    R.id.list_item_workout_exercise_title -> {
                        val direction = SettingsFragmentDirections.actionSettingsFragmentToSettingsDetailWorkoutFragment().setWorkoutId(workout.id_workout).setStatus(workout.status)
                        findNavController().navigate(direction)
                    }
                    R.id.list_item_workout_ic_delete -> {
                        workoutViewModel.deleteWorkout(workout.id_workout)
                    }
                }
            }
        )


        binding.workoutSettingsRecyclerView.layoutManager = linearLayoutManager
        binding.workoutSettingsRecyclerView.adapter = adapter

        binding.workoutSettingsChipGroup.setOnCheckedChangeListener{ group, checked ->
            when(checked) {
                R.id.workout_settings_chip_active -> {
                    workoutViewModel.setFilterStatus(true)
                    workoutViewModel.onFilterChange()
                }
                R.id.workout_settings_chip_inactive -> {
                    workoutViewModel.setFilterStatus(false)
                    workoutViewModel.onFilterChange()
                }
            }
        }

        workoutViewModel.workouts?.observe( viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                workoutViewModel.showAndHide(it.size)
            }
        })

        workoutViewModel.getFilterStatus().observe(viewLifecycleOwner, Observer {
            workoutViewModel.onFilterChange()
        })
        workoutViewModel.getFilterWkType().observe(viewLifecycleOwner, Observer {
            workoutViewModel.onFilterChange()
        })

        return binding.root
    }
}
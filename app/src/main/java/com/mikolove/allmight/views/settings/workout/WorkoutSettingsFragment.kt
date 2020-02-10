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
import com.mikolove.allmight.databinding.FragmentWorkoutSettingsBinding
import com.mikolove.allmight.views.settings.home.HomeSettingsFragmentDirections
import timber.log.Timber

class WorkoutSettingsFragment : Fragment() {

    private lateinit var viewModel : WorkoutSettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentWorkoutSettingsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_workout_settings, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = WorkoutSettingsViewModelFactory(dataSource, application)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WorkoutSettingsViewModel::class.java)

        binding.workoutSettingsViewModel = viewModel

        binding.lifecycleOwner = this
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = WorkoutSettingsAdapter(
            WorkoutSettingsListener { view: View, workout: Workout ->
                when(view.id){
                    R.id.list_item_workout_exercise_title -> {
                        Timber.i("Workout id sended %d",workout.id)
                        val direction = HomeSettingsFragmentDirections.actionHomeSettingsFragmentToDetailWorkoutSettingsFragment().setWorkoutId(workout.id).setStatus(workout.status)
                        findNavController().navigate(direction)
                    }
                    R.id.list_item_workout_ic_delete -> {
                        viewModel.deleteWorkout(workout.id)
                    }
                }
            }
        )


        binding.workoutSettingsRecyclerView.layoutManager = linearLayoutManager
        binding.workoutSettingsRecyclerView.adapter = adapter

        binding.workoutSettingsChipGroup.setOnCheckedChangeListener{ group, checked ->
            when(checked) {
                R.id.workout_settings_chip_active -> {
                    viewModel.setFilterStatus(true)
                    viewModel.onFilterChange()
                }
                R.id.workout_settings_chip_inactive -> {
                    viewModel.setFilterStatus(false)
                    viewModel.onFilterChange()
                }
            }
        }

        viewModel.workouts?.observe( viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                viewModel.showAndHide(it.size)
            }
        })

        viewModel.getFilterStatus().observe(viewLifecycleOwner, Observer {
            viewModel.onFilterChange()
        })
        viewModel.getFilterWkType().observe(viewLifecycleOwner, Observer {
            viewModel.onFilterChange()
        })

        return binding.root
    }
}
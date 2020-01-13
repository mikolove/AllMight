package com.mikolove.allmight.views.settings.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentWorkoutSettingsBinding
import com.mikolove.allmight.views.settings.home.HomeSettingsFragmentDirections
import timber.log.Timber

class WorkoutSettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentWorkoutSettingsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_workout_settings, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application).workoutDao()

        val viewModelFactory = WorkoutSettingsViewModelFactory(dataSource, application)

        val workoutSettingsViewModel = ViewModelProviders.of(this, viewModelFactory).get(WorkoutSettingsViewModel::class.java)

        binding.workoutSettingsViewModel = workoutSettingsViewModel

        binding.lifecycleOwner = this
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = WorkoutSettingsAdapter(
            WorkoutSettingsListener {
                workoutId ->
                Timber.i("Cliked row id : %d",workoutId)
                Timber.i("Current Dest id %s",findNavController().graph.toString())
                Timber.i("Dest id %s",R.id.detail_workout_settings_fragment.toString())

                val direction = HomeSettingsFragmentDirections.actionHomeSettingsFragmentToDetailWorkoutSettingsFragment().setWorkoutId(workoutId)
                findNavController().navigate(direction)
                //if (findNavController().currentDestination?.id == R.id.detail_workout_settings_fragment) {
                //findNavController().navigate(WorkoutSettingsFragmentDirections.actionWorkoutSettingsFragmentToDetailWorkoutSettingsFragment().setWorkoutId(workoutId))
                //}
                    //workoutSettingsViewModel.doneNavigatingToDetailWorkout()
                    //this.findNavController().navigate(HomeSettingsFragmentDirections.actionHomeSettingsFragmentToDetailWorkoutSettingsFragment().setWorkoutId(workoutId))
                    //workoutSettingsViewModel.doneNavigatingToDetailWorkout()
            }
        )

        binding.workoutSettingsRecyclerView.layoutManager = linearLayoutManager
        binding.workoutSettingsRecyclerView.adapter = adapter

        workoutSettingsViewModel.workouts.observe( viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
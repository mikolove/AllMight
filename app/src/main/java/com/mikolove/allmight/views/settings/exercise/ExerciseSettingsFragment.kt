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
import com.mikolove.allmight.databinding.FragmentExerciseSettingsBinding
import com.mikolove.allmight.views.settings.home.HomeSettingsFragmentDirections

class ExerciseSettingsFragment : Fragment() {

    private lateinit var viewModel : ExerciseSettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentExerciseSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_settings, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = ExerciseSettingsViewModelFactory(dataSource, application)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExerciseSettingsViewModel::class.java)

        binding.exerciseSettingsViewModel = viewModel

        binding.lifecycleOwner = this
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = ExerciseSettingsAdapter(
            ExerciseSettingsListener { view: View, exercise: Exercise ->
                when(view.getId()){
                    R.id.list_item_exercise_title -> {
                        val direction = HomeSettingsFragmentDirections.actionHomeSettingsFragmentToDetailExerciseSettingsFragment().setExerciseId(exercise.id).setStatus(exercise.status)
                        findNavController().navigate(direction)
                    }
                    R.id.list_item_exercise_ic_delete -> {
                        viewModel.deleteExercise(exercise.id)
                    }
                }
            }
        )

        binding.exerciseSettingsRecyclerView.layoutManager = linearLayoutManager
        binding.exerciseSettingsRecyclerView.adapter = adapter

        viewModel.exercises.observe( viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
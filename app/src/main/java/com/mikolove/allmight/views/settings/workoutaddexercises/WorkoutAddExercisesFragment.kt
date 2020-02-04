package com.mikolove.allmight.views.settings.workoutaddexercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.databinding.FragmentWorkoutAddExercisesBinding
import com.mikolove.allmight.views.settings.detail.DetailWorkoutSettingsFragmentArgs
import timber.log.Timber

class WorkoutAddExercisesFragment : Fragment(){

    private lateinit var viewModel : WorkoutAddExercisesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentWorkoutAddExercisesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_workout_add_exercises, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val arguments = DetailWorkoutSettingsFragmentArgs.fromBundle(arguments!!)

        val workoutId = arguments.workoutId

        val viewModelFactory = WorkoutAddExercisesViewModelFactory(workoutId, dataSource, application)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WorkoutAddExercisesViewModel::class.java)

        binding.workoutAddExerciseViewModel = viewModel
        binding.lifecycleOwner = this

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val adapter = WorkoutAddExercisesAdapter(
            WorkoutAddExercisesListener { view: View, exercise: AddExercise ->
                exercise?.let {
                   viewModel.switchState(exercise)
                }
            }
        )

        binding.wkaddexRecyclerView.layoutManager = linearLayoutManager
        binding.wkaddexRecyclerView.adapter = adapter

        binding.wkaddexChipGroup.setOnCheckedChangeListener{ group, checked ->
            when(checked) {
                R.id.wkaddex_chip_all -> {
                    viewModel.setFilterStatus(0)
                    viewModel.onFilterChange()
                }
                R.id.wkaddex_chip_selected -> {
                    viewModel.setFilterStatus(1)
                    viewModel.onFilterChange()
                }
            }
        }

        viewModel.exercises?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        
        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.notifyDataSetChanged()
                viewModel.stateHasChange()
                viewModel.unlockAction()
            }
        })

        return binding.root
    }

}

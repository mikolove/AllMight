package com.mikolove.allmight.views.routine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentRoutineBinding
import timber.log.Timber

class RoutineFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentRoutineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_routine,container,false)

        val extras = RoutineFragmentArgs.fromBundle(arguments!!)

        val routineId = extras.idRoutine

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = RoutineViewModelFactory(dataSource,routineId)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(RoutineViewModel::class.java)

        binding.routineViewModel = viewModel
        binding.lifecycleOwner = this

        val linearLayout = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = RoutineAdapter( RoutineClickListener {
                exercise ->
                    Timber.i("Exercise selected %d",exercise.id_exercise)
        })

        binding.routineRecyclerView.layoutManager = linearLayout
        binding.routineRecyclerView.adapter = adapter

        viewModel.workoutWithExercices.observe(viewLifecycleOwner, Observer {
            Timber.i("Workout name %s",it.workout.name)
            adapter.submitList(it.exercises)
        })

        viewModel.routine.observe(viewLifecycleOwner, Observer {
            routine ->
                Timber.i("Routine id %d Created_at %s Ended_at %s",routine.id_routine,routine.created_at.toString(),routine.ended_at.toString())
                viewModel.getLoadWorkout().value = routine.id_workout
        })

        return binding.root
    }
}
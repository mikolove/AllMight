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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentRoutineBinding
import timber.log.Timber

class RoutineFragment : Fragment() {

    //Current Routine
    var routineId : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentRoutineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_routine,container,false)

        val extras = RoutineFragmentArgs.fromBundle(arguments!!)

        routineId = extras.idRoutine

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = RoutineViewModelFactory(dataSource,routineId)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(RoutineViewModel::class.java)

        binding.routineViewModel = viewModel
        binding.lifecycleOwner = this

        val linearLayout = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = RoutineAdapter( RoutineClickListener {
                it ->
                    viewModel.goToProgress(it.routineExercise.id_routine_exercise)
                    Timber.i("Exercise selected %d",it.exercise.id_exercise)
        })

        binding.routineRecyclerView.layoutManager = linearLayout
        binding.routineRecyclerView.adapter = adapter

        viewModel.routineExercisesWithExercices.observe(viewLifecycleOwner, Observer {
            Timber.i("Size routine exercise list %d",it.size)
            adapter.submitList(it)
        })

        viewModel.routineWithWorkout.observe(viewLifecycleOwner, Observer {
            routineWithWorkout ->
                Timber.i("Routine id %d Created_at %s Ended_at %s",routineWithWorkout.routine.id_routine,routineWithWorkout.routine.created_at.toString(),routineWithWorkout.routine.ended_at.toString())
                viewModel.getLoadWorkout().value = routineWithWorkout.routine.id_routine
        })

        viewModel.navigateToProgress.observe(viewLifecycleOwner, Observer {idRoutineExercise ->
            if(idRoutineExercise != 0){
                this.findNavController().navigate(RoutineFragmentDirections.actionRoutineExerciseFragmentToExerciseProgressFragment(routineId,idRoutineExercise))
                viewModel.goToProgressDone()
            }
        })

        return binding.root
    }
}
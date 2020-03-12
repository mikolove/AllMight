package com.mikolove.allmight.views.routine.progress

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentProgressBinding
import timber.log.Timber

class ProgressFragment : Fragment() {

    private lateinit var binding : FragmentProgressBinding

    enum class ChronometerState{
        RUNNING,PAUSE,STOP
    }

    var chState : ChronometerState = ChronometerState.STOP
    lateinit var chronometer : Chronometer
    var pauseOffset : Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_progress, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val extras = ProgressFragmentArgs.fromBundle(arguments!!)
        val routineId = extras.idRoutine
        val routineExerciseId = extras.idRoutineExercise

        val viewModelFactory = ProgressViewModelFactory(dataSource,routineId,routineExerciseId)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(ProgressViewModel::class.java)

        binding.progressViewModel = viewModel
        binding.lifecycleOwner = this

        chronometer = binding.progressChronometer

        binding.progressChPlay.setOnClickListener{
            if(chState == ChronometerState.STOP ||chState == ChronometerState.PAUSE ){
                startChronometer()
                updateChButtons()
            }
        }

        binding.progressChPause.setOnClickListener{
            if(chState == ChronometerState.RUNNING){
                pauseChronometer()
                updateChButtons()
            }
        }

        binding.progressChReset.setOnClickListener{
            if(chState == ChronometerState.STOP ||chState == ChronometerState.PAUSE ){
                resetChronometer()
                updateChButtons()
            }
        }

        viewModel.routine.observe(viewLifecycleOwner, Observer { it ->
            Timber.i("Routine ID %d Workout %s", it.routine.id_routine,it.workout.name )
        })

        viewModel.routineExerciseWithSet.observe(viewLifecycleOwner, Observer { it ->
            Timber.i("Routine Exercise ID %d Exercise %s", it.routineExercise.id_routine_exercise, it.exercise.name )
            Timber.i("Sets count : %d",it.sets.size)
            it.sets.forEach { set ->
                Timber.i("Set %d Rep %d Created_at %s Ended_at %s",set.id_set,set.reps,set.created_at.toString(),set.ended_at.toString())
            }
        })
        updateChButtons()
        return binding.root
    }

    private fun startChronometer(){
        if(chState == ChronometerState.STOP || chState == ChronometerState.PAUSE){
            chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
            chronometer.start()
            chState = ChronometerState.RUNNING
        }
    }

    private fun pauseChronometer(){
        if(chState == ChronometerState.RUNNING){
            chronometer.stop()
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
            chState = ChronometerState.PAUSE
        }
    }

    private fun resetChronometer(){
        if(chState == ChronometerState.PAUSE || chState == ChronometerState.STOP){
            pauseOffset = 0
            chronometer.base = SystemClock.elapsedRealtime()
            chState = ChronometerState.STOP
        }
    }

    private fun updateChButtons(){
        when(chState){
            ChronometerState.RUNNING -> {
                binding.progressChPlay.isEnabled = false
                binding.progressChReset.isEnabled = false
                binding.progressChPause.isEnabled = true
            }
            ChronometerState.PAUSE -> {
                binding.progressChPlay.isEnabled = true
                binding.progressChReset.isEnabled = true
                binding.progressChPause.isEnabled = false
            }
            ChronometerState.STOP -> {
                binding.progressChPlay.isEnabled = true
                binding.progressChReset.isEnabled = true
                binding.progressChPause.isEnabled = false
            }
        }
    }
}
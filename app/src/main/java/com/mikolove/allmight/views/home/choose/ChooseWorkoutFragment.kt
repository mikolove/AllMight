package com.mikolove.allmight.views.home.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentChooseWorkoutBinding
import timber.log.Timber

class ChooseWorkoutFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentChooseWorkoutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_workout,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = ChooseWorkoutViewModelFactory(dataSource, application)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChooseWorkoutViewModel::class.java)

        binding.chooseWorkoutViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.workouts.observe(viewLifecycleOwner, Observer { list ->
            list.forEach { wkExercises ->
                Timber.i("Workout name %s Exercises %d",wkExercises.workout.name,wkExercises.exercises.size)
            }
        })

        return binding.root
    }


}
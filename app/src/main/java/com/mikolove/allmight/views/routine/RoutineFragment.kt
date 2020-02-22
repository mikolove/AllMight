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

        val workoutId = extras.idWorkout

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = RoutineViewModelFactory(dataSource,workoutId)

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
            Timber.i("it name %s",it.workout.name)
            adapter.submitList(it.exercises)
        })

        return binding.root
    }
}
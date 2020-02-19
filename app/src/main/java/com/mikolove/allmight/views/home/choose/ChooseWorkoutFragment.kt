package com.mikolove.allmight.views.home.choose

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

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val adapter = ChooseWorkoutAdapter( ChooseWorkoutListener {
            workout ->
                Timber.i("Workout choose %d Exercise %d Type %s ",workout.workout.id_workout, workout.exercises.size, workout.workout_type.name)
        })

        binding.chooseWorkoutRecyclerView.layoutManager = linearLayoutManager
        binding.chooseWorkoutRecyclerView.adapter = adapter

        viewModel.workouts?.observe(viewLifecycleOwner, Observer { list ->
            adapter.submitList(list)
            viewModel.showAndHide(list.size)
        })

        viewModel.getFilterWkType().observe(viewLifecycleOwner, Observer {
            viewModel.onFilterChange()
        })

        return binding.root
    }


}
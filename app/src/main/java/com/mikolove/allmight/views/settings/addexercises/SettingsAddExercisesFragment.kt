package com.mikolove.allmight.views.settings.addexercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.databinding.FragmentSettingsAddExercisesBinding
import com.mikolove.allmight.views.settings.detail.SettingsDetailWorkoutFragmentArgs

class SettingsAddExercisesFragment : Fragment(){

    private lateinit var viewModel : SettingsAddExercisesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentSettingsAddExercisesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings_add_exercises, container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val arguments = SettingsDetailWorkoutFragmentArgs.fromBundle(arguments!!)

        val workoutId = arguments.workoutId

        val viewModelFactory = SettingsAddExercisesViewModelFactory(workoutId, dataSource, application)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsAddExercisesViewModel::class.java)

        binding.workoutAddExerciseViewModel = viewModel
        binding.lifecycleOwner = this

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val adapter = WorkoutAddExercisesAdapter(
            SettingsAddExercisesListener { view: View, exercise: AddExercise ->
                exercise.let {
                   viewModel.switchState(exercise)
                }
            }
        )

        binding.wkaddexRecyclerView.layoutManager = linearLayoutManager
        binding.wkaddexRecyclerView.adapter = adapter

        binding.wkaddexChipGroup.setOnCheckedChangeListener{ group, checked ->

            binding.wkaddexSearchView.isIconified = true
            when(checked) {
                R.id.wkaddex_chip_all -> {
                    viewModel.setFilterStatus(0)
                }
                R.id.wkaddex_chip_selected -> {
                    viewModel.setFilterStatus(1)
                }
            }
        }

        viewModel.exercises.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.loadData(it)
                viewModel.showAndHide(it.size)
            }
        })


        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.notifyDataSetChanged()
                viewModel.stateHasChange()
            }
        })

        binding.wkaddexSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
               return false
            }
        })

        return binding.root
    }

}

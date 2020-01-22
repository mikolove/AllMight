package com.mikolove.allmight.views.settings.detail

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentDetailsWorkoutSettingBinding

class DetailWorkoutSettingsFragment : Fragment(){

    private lateinit var binding : FragmentDetailsWorkoutSettingBinding
    private lateinit var viewmodel : DetailWorkoutSettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_workout_setting, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val arguments = DetailWorkoutSettingsFragmentArgs.fromBundle(arguments!!)

        val workoutId = arguments.workoutId

        val viewModelFactory = DetailWorkoutSettingsViewModelFactory(workoutId,dataSource, application)

        setHasOptionsMenu(true)

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(DetailWorkoutSettingsViewModel::class.java)

        binding.detailWorkoutSettingsViewModel = viewmodel
        binding.lifecycleOwner = this

        viewmodel.workout.observe(this, Observer {
            viewmodel.loadWorkoutType()
        })

        viewmodel.getListWorkoutType().observe(this, Observer {
            viewmodel.loadWorkoutType()
        })

        viewmodel.getWorkoutType().observe(this, Observer {
            it?.let{
                viewmodel.updateWorkoutType()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.detail_workout__action_insert -> {
                viewmodel.insertWorkout()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}
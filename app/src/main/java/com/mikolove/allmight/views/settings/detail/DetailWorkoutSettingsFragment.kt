package com.mikolove.allmight.views.settings.detail

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutType
import com.mikolove.allmight.databinding.FragmentDetailsWorkoutSettingBinding
import timber.log.Timber

class DetailWorkoutSettingsFragment : Fragment(){


    private lateinit var binding : FragmentDetailsWorkoutSettingBinding
    private lateinit var viewmodel : DetailWorkoutSettingsViewModel
    private lateinit var workout : Workout

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
        binding.setLifecycleOwner(this)


        viewmodel.getWorkout().observe(this, Observer {
            //setWorkoutTypeSpinner(viewmodel.getWorkout().value , viewmodel.getWorkoutType().value as WorkoutType?)
        })

        viewmodel.getListWorkoutType().observe(this, Observer {
            viewmodel.loadWorkoutType()
            //setWorkoutTypeSpinner(viewmodel.getWorkout().value , viewmodel.getWorkoutType().value as WorkoutType?)
        })

        viewmodel.getWorkoutType().observe(this, Observer { it ->
            it?.let{
                Timber.i("Workout Type Observed id %d name %s",viewmodel.getWorkoutType().value?.getObjectId(), viewmodel.getWorkoutType().value?.getObjectId())
            }
        })

        return binding.root
    }

    private fun setWorkoutTypeSpinner(wk: Workout?, wkType: WorkoutType?){

        Timber.i("Value for setWorkoutTypeSpinner wk %s wkType %s",wk.toString(),wkType.toString())
        //if( wk == null) return

        //if(wkType == null) viewmodel.loadWorkoutType(wk.id_workout_type)

        //if(wk.id_workout_type != wkType?.id) viewmodel.loadWorkoutType(wk.id_workout_type)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.detail_workout__action_insert -> {
                binding.detailWorkoutSettingsViewModel!!.onInsert(viewmodel.getWorkout().value!!)
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}
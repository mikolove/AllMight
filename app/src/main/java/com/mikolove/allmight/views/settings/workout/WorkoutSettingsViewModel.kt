package com.mikolove.allmight.views.settings.workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mikolove.allmight.database.dao.WorkoutDao
import timber.log.Timber

class WorkoutSettingsViewModel(val database: WorkoutDao, application: Application) : AndroidViewModel(application) {

    val workouts = database.getAllWorkout(true)

    private val _navigateToDetailWorkout = MutableLiveData<Int>()
    val navigateToDetailWorkout: LiveData<Int>
        get() = _navigateToDetailWorkout

    fun doneNavigatingToDetailWorkout() {
        Timber.i("Zob end Value set to null")
        _navigateToDetailWorkout.value = null
    }

    fun onEditWorkoutClick(workoutId : Int = 0){
        _navigateToDetailWorkout.value = workoutId
        Timber.i("Zob Value set to %d",_navigateToDetailWorkout.value)
    }
}
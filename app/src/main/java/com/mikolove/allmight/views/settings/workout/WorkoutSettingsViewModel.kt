package com.mikolove.allmight.views.settings.workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.dao.WorkoutDao
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.repository.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class WorkoutSettingsViewModel(val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    private val wkRepo = WorkoutRepository(database)
    val workouts = wkRepo.getAllWorkout()

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

    fun deleteWorkout(workoutId : Int){
        workoutId.let{
            viewModelScope.launch {
                delete(it)
            }
        }
    }

    private suspend fun delete(workoutId : Int) {
        withContext(Dispatchers.IO) {
            wkRepo.delete(workoutId)
        }
    }
}
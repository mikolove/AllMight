package com.mikolove.allmight.views.settings.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExerciseSettingsViewModel (val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    private val exRepo = ExerciseRepository(database)
    val exercises = exRepo.getAllExercise()

    private val _navigateToDetailWorkout = MutableLiveData<Int>()
    val navigateToDetailWorkout: LiveData<Int>
        get() = _navigateToDetailWorkout

    fun doneNavigatingToDetailWorkout() {
        _navigateToDetailWorkout.value = null
    }

    fun onEditWorkoutClick(workoutId : Int = 0){
        _navigateToDetailWorkout.value = workoutId
    }

    fun deleteExercise(workoutId : Int){
        workoutId.let{
            viewModelScope.launch {
                delete(it)
            }
        }
    }

    private suspend fun delete(workoutId : Int) {
        withContext(Dispatchers.IO) {
            exRepo.delete(workoutId)
        }
    }
}
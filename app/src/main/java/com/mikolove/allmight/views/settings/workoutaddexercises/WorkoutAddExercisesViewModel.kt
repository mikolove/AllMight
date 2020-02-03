package com.mikolove.allmight.views.settings.workoutaddexercises

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.WorkoutRepository

class WorkoutAddExercisesViewModel(val workoutId : Int, val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    val exRepo = ExerciseRepository(database)
    val wkRepo = WorkoutRepository(database)

    val workout = wkRepo.getWorkoutById(workoutId)
    val exercises = exRepo.getAddExercise(workoutId)

    val state = MutableLiveData<Boolean>()

    fun stateHasChange(){
        state.value = null
    }

    fun switchState(exercise : AddExercise){
         exercise?.let {
            if(it.is_selected == 0)
                it.is_selected = 1
            else
                it.is_selected = 0
        }
        state.value = true
    }

}
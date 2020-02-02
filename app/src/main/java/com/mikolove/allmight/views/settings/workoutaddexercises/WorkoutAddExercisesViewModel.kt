package com.mikolove.allmight.views.settings.workoutaddexercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.repository.ExerciseRepository

class WorkoutAddExercisesViewModel(val workoutId : Int, val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    val exRepo = ExerciseRepository(database)

    val exercises = exRepo.getAddExercise(workoutId)
}
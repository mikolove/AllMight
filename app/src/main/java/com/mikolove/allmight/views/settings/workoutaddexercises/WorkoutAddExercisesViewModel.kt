package com.mikolove.allmight.views.settings.workoutaddexercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.WorkoutRepository

class WorkoutAddExercisesViewModel(val workoutId : Int, val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    val exRepo = ExerciseRepository(database)
    val wkRepo = WorkoutRepository(database)

    val workout = wkRepo.getWorkoutById(workoutId)
    val exercises = exRepo.getAddExercise(workoutId)

}
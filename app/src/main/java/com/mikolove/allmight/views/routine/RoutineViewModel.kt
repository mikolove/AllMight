package com.mikolove.allmight.views.routine

import androidx.lifecycle.ViewModel
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.repository.WorkoutRepository

class RoutineViewModel(val database: AllmightDatabase, val id_workout : Int) : ViewModel() {

    val wkRepo = WorkoutRepository(database)

    val workoutWithExercices = wkRepo.getWorkoutWithExercisesById(id_workout)

}
package com.mikolove.allmight.views.routine

import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutWithExercises
import com.mikolove.allmight.repository.RoutineRepository
import com.mikolove.allmight.repository.WorkoutRepository

class RoutineViewModel(val database: AllmightDatabase, val id_routine : Int) : ViewModel() {

    val wkRepo = WorkoutRepository(database)
    val rtRepo = RoutineRepository(database)

    val routine = rtRepo.getRoutineById(id_routine)
    private val loadWorkout = MutableLiveData<Int>()
    fun getLoadWorkout() = loadWorkout

    val workoutWithExercices : LiveData<WorkoutWithExercises> = Transformations.switchMap(loadWorkout) {
        wkRepo.getWorkoutWithExercisesById(it)
    }

}
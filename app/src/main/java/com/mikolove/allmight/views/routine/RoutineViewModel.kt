package com.mikolove.allmight.views.routine

import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.RoutineExercise
import com.mikolove.allmight.database.entities.RoutineExerciseWithExercise
import com.mikolove.allmight.database.entities.WorkoutWithExercises
import com.mikolove.allmight.repository.RoutineRepository
import com.mikolove.allmight.repository.WorkoutRepository

class RoutineViewModel(val database: AllmightDatabase, val id_routine : Int) : ViewModel() {

    val rtRepo = RoutineRepository(database)

    val routineWithWorkout = rtRepo.getRoutineById(id_routine)
    private val loadWorkout = MutableLiveData<Int>()
    fun getLoadWorkout() = loadWorkout

    val routineExercisesWithExercices : LiveData<List<RoutineExerciseWithExercise>> = Transformations.switchMap(loadWorkout) {
        rtRepo.getRoutineExerciseWithExercise(it)
    }

    private val _navigateToProgress = MutableLiveData<Int>()
    val navigateToProgress : LiveData<Int>
        get() = _navigateToProgress

    fun goToProgress(id_exercise : Int){
        _navigateToProgress.value = id_exercise
    }

    fun goToProgressDone(){
        _navigateToProgress.value = 0
    }

}
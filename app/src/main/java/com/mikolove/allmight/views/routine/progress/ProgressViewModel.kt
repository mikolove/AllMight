package com.mikolove.allmight.views.routine.progress

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.RoutineExerciseSet
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.RoutineRepository

class ProgressViewModel(private val dataSource : AllmightDatabase, private val id_routine : Int , private val id_routine_exercise : Int) : ViewModel() {

    val exRepo = ExerciseRepository(dataSource)
    val rtRepo =  RoutineRepository(dataSource)

    val routine = rtRepo.getRoutineById(id_routine)
    val routineExerciseWithSet = rtRepo.getRoutineExerciseWithSet(id_routine_exercise)

    val listSetDone = MediatorLiveData<List<RoutineExerciseSet>>()

    fun initRoutineExercise() {}
    fun initSet(){}
    fun updateRoutineExercise(){}
    fun updateSet(){}
}
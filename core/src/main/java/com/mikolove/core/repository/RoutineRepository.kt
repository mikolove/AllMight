package com.mikolove.core.repository

import com.mikolove.core.data.Routine
import com.mikolove.core.data.RoutineExercise
import com.mikolove.core.data.RoutineExerciseSet

class RoutineRepository(private val routineDataSource: RoutineDataSource) {

    suspend fun createRoutine(routine: Routine) = routineDataSource.createRoutine(routine)

    suspend fun createRoutineExercise(routineExercises: List<RoutineExercise>) =
        routineDataSource.createRoutineExercise(routineExercises)

    suspend fun createRoutineExerciseSet(routineExerciseSets: List<RoutineExerciseSet>) =
        routineDataSource.createRoutineExerciseSet(routineExerciseSets)

    suspend fun getRoutine(id: Long): Routine? = routineDataSource.getRoutine(id)

    suspend fun getAllRoutine(): List<Routine>? = routineDataSource.getAllRoutines()


}
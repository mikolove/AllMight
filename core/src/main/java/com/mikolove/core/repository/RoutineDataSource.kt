package com.mikolove.core.repository

import com.mikolove.core.data.Routine
import com.mikolove.core.data.RoutineExercise
import com.mikolove.core.data.RoutineExerciseSet

interface RoutineDataSource {

    suspend fun createRoutine(routine : Routine)

    suspend fun createRoutineExercise(routineExercise: List<RoutineExercise>)

    suspend fun createRoutineExerciseSet(routineExerciseSets: List<RoutineExerciseSet>)

    suspend fun getRoutine(id : Long) : Routine?

    suspend fun getAllRoutines() : List<Routine>?
}
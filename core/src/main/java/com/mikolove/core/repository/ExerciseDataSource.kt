package com.mikolove.core.repository

import com.mikolove.core.data.Exercise
import com.mikolove.core.data.Set

interface ExerciseDataSource {

    suspend fun updateExercise(exercise: Exercise)

    suspend fun removeExercise(id :Long)

    suspend fun addSets(sets : List<Set>)

    suspend fun removeSets(sets : List<Set>)

    suspend fun getAllExercise() : List<Exercise>?

    suspend fun getExercise(id : Long) : Exercise?

}
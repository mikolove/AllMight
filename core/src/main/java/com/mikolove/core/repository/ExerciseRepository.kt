package com.mikolove.core.repository

import com.mikolove.core.data.Exercise
import com.mikolove.core.data.Set

class ExerciseRepository(private val exerciseDataSource: ExerciseDataSource) {

    suspend fun updateExercise(exercise: Exercise) = exerciseDataSource.updateExercise(exercise)

    suspend fun removeExercise(id: Long) = exerciseDataSource.removeExercise(id)

    suspend fun addSets(sets: List<Set>) = exerciseDataSource.addSets(sets)

    suspend fun removeSets(sets: List<Set>) = exerciseDataSource.removeSets(sets)

    suspend fun getAllExercises(): List<Exercise>? = exerciseDataSource.getAllExercise()

    suspend fun getExercise(id: Long): Exercise? = exerciseDataSource.getExercise(id)

}
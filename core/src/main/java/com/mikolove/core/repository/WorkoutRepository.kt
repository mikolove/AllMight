package com.mikolove.core.repository

import com.mikolove.core.data.Exercise
import com.mikolove.core.data.Workout

class WorkoutRepository(private val workoutDataSource: WorkoutDataSource) {

    suspend fun updateWorkout(workout: Workout) = workoutDataSource.updateWorkout(workout)

    suspend fun removeWorkout(id: Long) = workoutDataSource.removeWorkout(id)

    suspend fun addExercises(exercises: List<Exercise>) = workoutDataSource.addExercises(exercises)

    suspend fun removeExercises(exercises: List<Exercise>) =
        workoutDataSource.removeExercises(exercises)

    suspend fun getAllWorkout(): List<Workout>? = workoutDataSource.getAllWorkout()

    suspend fun getWorkout(id: Long): Workout? = workoutDataSource.getWorkout(id)
}
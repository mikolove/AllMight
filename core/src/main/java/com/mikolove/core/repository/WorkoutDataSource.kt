package com.mikolove.core.repository

import com.mikolove.core.data.Exercise
import com.mikolove.core.data.Workout

interface WorkoutDataSource {

    suspend fun updateWorkout(workout : Workout)

    suspend fun removeWorkout(id :Long)

    suspend fun addExercises(exercises : List<Exercise>)

    suspend fun removeExercises(exercises: List<Exercise>)

    suspend fun getAllWorkout() : List<Workout>?

    suspend fun getWorkout(id : Long) : Workout?

}
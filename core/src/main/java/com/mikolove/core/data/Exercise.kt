package com.mikolove.core.data


data class Exercise(
    var idExercise: Long = 0L,
    var name: String = "",
    var sets: List<Set>,
    var workoutType: WorkoutType,
    var isActive: Boolean = true,
    var created_at: String,
    var updated_at: String)
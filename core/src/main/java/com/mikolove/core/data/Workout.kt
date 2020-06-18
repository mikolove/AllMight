package com.mikolove.core.data

data class Workout(
    var idWorkout: Long = 0L,
    var name: String = "",
    var exercises: List<Exercise>,
    var workoutType: WorkoutType,
    var isActive: Boolean = true,
    var created_at: String,
    var updated_at: String
)
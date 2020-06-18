package com.mikolove.core.data

data class Routine(
    var idRoutine: Long = 0L,
    var workout: Workout,
    var routineExercises: List<RoutineExercise>,
    var created_at: String,
    var updated_at: String
)
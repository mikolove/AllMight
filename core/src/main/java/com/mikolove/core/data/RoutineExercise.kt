package com.mikolove.core.data

class RoutineExercise(
    var idRoutineExercise: Long = 0L,
    var exercise: Exercise,
    var sets: List<RoutineExerciseSet>,
    var created_at: String,
    var updated_at: String
)
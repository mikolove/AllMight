package com.mikolove.allmight.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import java.util.*

data class RoutineExerciseWithExercise (

    @Embedded val routineExercise : RoutineExercise = RoutineExercise(created_at = Date(),ended_at = null),

    @Relation(
        parentColumn = "id_exercise",
        entityColumn = "id_exercise"
    )
    val exercise : Exercise = Exercise()
)
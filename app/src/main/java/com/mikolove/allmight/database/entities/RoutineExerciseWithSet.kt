package com.mikolove.allmight.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import java.util.*

data class RoutineExerciseWithSet (

    @Embedded val routineExercise : RoutineExercise = RoutineExercise(created_at = Date()),

    @Relation(
        parentColumn = "id_exercise",
        entityColumn = "id_exercise"
    )
    val exercise : Exercise = Exercise(),

    @Relation(
        parentColumn = "id_routine_exercise",
        entityColumn = "id_routine_exercise")
    val sets : List<RoutineExerciseSet> = mutableListOf()
)
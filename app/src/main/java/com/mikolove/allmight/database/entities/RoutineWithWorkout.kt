package com.mikolove.allmight.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import java.util.*

data class RoutineWithWorkout (

    @Embedded val routine : Routine = Routine(created_at = Date()),

    @Relation(
        parentColumn = "id_workout",
        entityColumn = "id_workout"
    )
    val workout : Workout = Workout()
)
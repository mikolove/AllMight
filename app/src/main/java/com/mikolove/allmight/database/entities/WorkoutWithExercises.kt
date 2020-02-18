package com.mikolove.allmight.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class WorkoutWithExercises(
    @Embedded val workout: Workout,

    @Relation(
        parentColumn = "id_workout",
        entityColumn = "id_exercise",
        associateBy = Junction(WorkoutExercise::class)
    )
    val exercises : List<Exercise>
)
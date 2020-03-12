package com.mikolove.allmight.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class WorkoutWithExercises(
    @Embedded val workout: Workout = Workout(),

    @Relation(
        parentColumn = "id_workout",
        entityColumn = "id_exercise",
        associateBy = Junction(WorkoutExercise::class)
    )
    val exercises : List<Exercise> = mutableListOf(),

    @Relation(
        parentColumn = "id_workout_type",
        entityColumn = "id_workout_type"
    )
    val workout_type : WorkoutType = WorkoutType()
) : BasicInfo {
    override fun getObjectId() = workout.getObjectId()

    override fun getObjectName() = workout.getObjectName()
}
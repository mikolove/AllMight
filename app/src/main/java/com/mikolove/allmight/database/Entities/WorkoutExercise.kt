package com.mikolove.allmight.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.mikolove.allmight.database.AllmightDatabase


@Entity(
    tableName = AllmightDatabase.workoutExerciseTableName,
    primaryKeys = arrayOf("id_exercise","id_workout"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = WorkoutType::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_workout")
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_exercise")
        )
    )
)
data class WorkoutExercise(

    @ColumnInfo(name = "id_workout")
    var id_workout : Int,

    @ColumnInfo(name = "id_exercise")
    var id_exercise : Int

)
package com.mikolove.allmight.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.mikolove.allmight.database.AllmightDatabase


@Entity(
    tableName = AllmightDatabase.workoutExerciseTableName,
    indices = [Index("id_exercise"), Index("id_workout")],
    primaryKeys = arrayOf("id_exercise","id_workout"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Workout::class,
            parentColumns = arrayOf("id_workout"),
            childColumns = arrayOf("id_workout")
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id_exercise"),
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
package com.mikolove.allmight.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.mikolove.allmight.database.AllmightDatabase
import java.util.*

@Entity(
    tableName = AllmightDatabase.routineExerciseSetTableName,
    indices = [Index("id_set")],
    primaryKeys = arrayOf("id_set","id_routine_exercise"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = RoutineExercise::class,
            parentColumns = arrayOf("id_routine_exercise"),
            childColumns = arrayOf("id_routine_exercise")
        )
    )
)
data class RoutineExerciseSet (

    @ColumnInfo(name = "id_set")
    var id_set : Int,

    @ColumnInfo(name = "reps")
    var reps : Int,

    @ColumnInfo(name = "weight")
    var weight : Int,

    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    var created_at : Date,

    @ColumnInfo(name = "started_at", defaultValue = "NULL")
    var started_at : Date? = null,

    @ColumnInfo(name = "ended_at", defaultValue = "NULL")
    var ended_at : Date? = null,

    @ColumnInfo(name = "id_routine_exercise", index = true)
    var id_routine_exercise : Int
)
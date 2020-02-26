package com.mikolove.allmight.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.mikolove.allmight.database.AllmightDatabase
import java.util.*

@Entity(
    tableName = AllmightDatabase.routineExerciseTypeTableName,
    indices = [Index("id_routine"),Index("id_set")],
    primaryKeys = arrayOf("id_routine","id_exercise","id_set"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Routine::class,
            parentColumns = arrayOf("id_routine"),
            childColumns = arrayOf("id_routine")
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id_exercise"),
            childColumns = arrayOf("id_exercise")
        )
    )
)
data class RoutineExercise (

    @ColumnInfo(name = "id_routine")
    var id_routine : Int,

    @ColumnInfo(name = "id_set")
    var id_set : Int,

    @ColumnInfo(name = "nb_reps")
    var nb_reps : Int,

    @ColumnInfo(name = "weight")
    var weight : Int,

    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    var created_at : Date,

    @ColumnInfo(name = "ended_at", defaultValue = "NULL")
    var ended_at : Date? = null,

    @ColumnInfo(name = "id_exercise", index = true)
    var id_exercise : Int
)
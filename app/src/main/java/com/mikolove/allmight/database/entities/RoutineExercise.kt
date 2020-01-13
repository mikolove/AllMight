package com.mikolove.allmight.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.mikolove.allmight.database.AllmightDatabase
import java.util.*

@Entity(
    tableName = AllmightDatabase.routineExerciseTypeTableName,
    indices = [Index("id_routine"),Index("created_at")],
    primaryKeys = arrayOf("id_routine","created_at"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Routine::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_routine")
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_exercise")
        )
    )
)
data class RoutineExercise (

    @ColumnInfo(name = "id_routine")
    var id_routine : Int,

    @ColumnInfo(name = "created_at")
    var created_at : Int,

    @ColumnInfo(name = "ended_at")
    var ended_at : Date,

    @ColumnInfo(name = "nb_reps")
    var nb_reps : Int,

    @ColumnInfo(name = "nb_series")
    var nb_series : Int,

    @ColumnInfo(name = "id_exercise")
    var id_exercise : Int
)
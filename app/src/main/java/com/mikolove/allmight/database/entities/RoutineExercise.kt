package com.mikolove.allmight.database.entities

import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import java.util.*

@Entity(
    tableName = AllmightDatabase.routineExerciseTableName,
    indices = [Index("id_routine_exercise")],
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

    @PrimaryKey(autoGenerate = true)
    var id_routine_exercise : Int = 0,

    @ColumnInfo(name = "nb_reps")
    var nb_reps : Int = 0,

    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    var created_at : Date,

    @ColumnInfo(name = "started_at", defaultValue = "NULL")
    var started_at : Date? = null,

    @ColumnInfo(name = "ended_at", defaultValue = "NULL")
    var ended_at : Date? = null,

    @ColumnInfo(name = "id_routine", index = true)
    var id_routine : Int = 0,

    @ColumnInfo(name = "id_exercise", index = true)
    var id_exercise : Int = 0
)
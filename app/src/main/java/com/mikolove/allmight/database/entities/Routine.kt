package com.mikolove.allmight.database.entities

import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import java.util.*

@Entity(tableName = AllmightDatabase.routineTableName,
    indices = [Index("id")],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Workout::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_workout")
        )
    )
)
data class Routine(

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "id_workout", index = true)
    var id_workout : Int,

    @ColumnInfo(name = "created_at")
    var created_at : Date,

    @ColumnInfo(name = "ended_at")
    var ended_at : Date
)
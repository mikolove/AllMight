package com.mikolove.allmight.database.entities

import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import java.util.*

@Entity(tableName = AllmightDatabase.routineTableName,
    indices = [Index("id_routine")],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Workout::class,
            parentColumns = arrayOf("id_workout"),
            childColumns = arrayOf("id_workout")
        )
    )
)
data class Routine(

    @PrimaryKey(autoGenerate = true)
    var id_routine : Int = 0,

    @ColumnInfo(name = "id_workout", index = true)
    var id_workout : Int,

    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    var created_at : Date,

    @ColumnInfo(name = "ended_at", defaultValue = "NULL")
    var ended_at : Date? = null
)
package com.mikolove.allmight.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mikolove.allmight.database.AllmightDatabase

@Entity(
    tableName = AllmightDatabase.workoutTableName,
    foreignKeys = arrayOf(
        ForeignKey(
            entity = WorkoutType::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_workout_type")
        )
    )
)
data class Workout(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "name")
    var name : String?,

    @ColumnInfo(name = "status")
    var status : Boolean = true,

    @ColumnInfo(name = "id_workout_type")
    var id_workout_type : Int
)
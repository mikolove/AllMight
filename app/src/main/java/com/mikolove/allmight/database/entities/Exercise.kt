package com.mikolove.allmight.database.entities

import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase

@Entity(
    tableName = AllmightDatabase.exerciseTableName,
    indices = [Index("id")],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = WorkoutType::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_workout_type")
        )
    )
)
data class Exercise(

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "name")
    var name : String? = null,

    @ColumnInfo(name = "series_count")
    var series_count : Int = 0,

    @ColumnInfo(name = "rep_count")
    var rep_count : Int = 0,

    @ColumnInfo(name = "status")
    var status : Boolean = true,

    @ColumnInfo(name = "id_workout_type")
    var id_workout_type : Int = 0

)

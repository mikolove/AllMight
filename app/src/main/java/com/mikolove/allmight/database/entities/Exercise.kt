package com.mikolove.allmight.database.entities

import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase

@Entity(
    tableName = AllmightDatabase.exerciseTableName,
    indices = [Index("id_exercise")],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = WorkoutType::class,
            parentColumns = arrayOf("id_workout_type"),
            childColumns = arrayOf("id_workout_type")
        )
    )
)
data class Exercise(

    @PrimaryKey(autoGenerate = true)
    var id_exercise : Int = 0,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "series_count")
    var series_count : Int = 0,

    @ColumnInfo(name = "rep_count")
    var rep_count : Int = 0,

    @ColumnInfo(name = "status")
    var status : Boolean = true,

    @ColumnInfo(name = "id_workout_type", index = true)
    var id_workout_type : Int = 0

) : BasicInfo{
    override fun getObjectId() = id_exercise

    override fun getObjectName() = name
}

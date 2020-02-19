package com.mikolove.allmight.database.entities

import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase

@Entity(
    tableName = AllmightDatabase.workoutTableName,
    indices = [Index("id_workout")],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = WorkoutType::class,
            parentColumns = arrayOf("id_workout_type"),
            childColumns = arrayOf("id_workout_type")
        )
    )
)
data class Workout(
    @PrimaryKey(autoGenerate = true)
    var id_workout : Int = 0,

    @ColumnInfo(name = "name")
    var name : String = "",

    @ColumnInfo(name = "status")
    var status : Boolean = true,

    @ColumnInfo(name = "id_workout_type" , index = true)
    var id_workout_type : Int = 0
) : BasicInfo{
    override fun getObjectId(): Int = id_workout

    override fun getObjectName(): String = name

}
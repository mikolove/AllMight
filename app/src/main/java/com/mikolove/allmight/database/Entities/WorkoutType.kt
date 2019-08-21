package com.mikolove.allmight.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mikolove.allmight.database.AllmightDatabase

@Entity(tableName = AllmightDatabase.workoutTypeTableName)
data class WorkoutType(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "name")
    var name : String
)
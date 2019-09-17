package com.mikolove.allmight.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mikolove.allmight.database.AllmightDatabase

@Entity(tableName = AllmightDatabase.workoutTypeTableName,
        indices = [Index("id")])
data class WorkoutType(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "name")
    var name : String
)
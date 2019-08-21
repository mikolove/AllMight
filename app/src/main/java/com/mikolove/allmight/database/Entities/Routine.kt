package com.mikolove.allmight.database.Entities

import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import java.util.Date

@Entity(tableName = AllmightDatabase.routineTableName)
data class Routine(

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "created_at")
    var created_at : Date,

    @ColumnInfo(name = "ended_at")
    var ended_at : Date
)
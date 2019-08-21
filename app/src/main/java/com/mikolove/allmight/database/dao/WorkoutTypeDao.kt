package com.mikolove.allmight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.Entities.WorkoutType

@Dao
interface WorkoutTypeDao {

    @Insert
    fun insert(workoutType: WorkoutType)

    @Update
    fun update(workoutType: WorkoutType)

    @Query("DELETE FROM ${AllmightDatabase.workoutTypeTableName}")
    fun clear()
}
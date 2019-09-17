package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutType

@Dao
interface WorkoutTypeDao {

    @Insert
    fun insert(workoutType: WorkoutType)

    @Update
    fun update(workoutType: WorkoutType)

    @Query("DELETE FROM ${AllmightDatabase.workoutTypeTableName}")
    fun clear()

    @Query("DELETE FROM ${AllmightDatabase.workoutTypeTableName} WHERE id = :id")
    fun clearById(id : Int)

    @Query("SELECT * FROM ${AllmightDatabase.workoutTypeTableName}")
    fun getAllWorkoutType() : LiveData<List<WorkoutType>>
}
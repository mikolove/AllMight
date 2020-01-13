package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutType

@Dao
interface WorkoutTypeDao {

    @Insert
    fun insert(workoutType: WorkoutType)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(workoutType: List<WorkoutType>)

    @Update
    fun update(workoutType: WorkoutType)

    @Query("DELETE FROM ${AllmightDatabase.workoutTypeTableName}")
    fun clear()

    @Query("DELETE FROM ${AllmightDatabase.workoutTypeTableName} WHERE id = :id")
    fun clearById(id : Int)

    @Query("SELECT * FROM ${AllmightDatabase.workoutTypeTableName}")
    fun getAllWorkoutType() : LiveData<List<WorkoutType>>
}
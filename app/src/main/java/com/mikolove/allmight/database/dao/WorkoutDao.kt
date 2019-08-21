package com.mikolove.allmight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.Entities.Workout

@Dao
interface WorkoutDao {

    @Insert
    fun insert(workout: Workout)

    @Update
    fun update(workout: Workout)

    @Query("DELETE FROM ${AllmightDatabase.workoutTableName}")
    fun clear()
}

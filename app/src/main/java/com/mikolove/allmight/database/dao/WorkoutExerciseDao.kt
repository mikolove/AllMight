package com.mikolove.allmight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.Entities.WorkoutExercise

@Dao
interface WorkoutExerciseDao{

    @Insert
    fun insert(workoutExercise: WorkoutExercise)

    @Update
    fun update(workoutExercise: WorkoutExercise)

    @Query("DELETE FROM ${AllmightDatabase.workoutExerciseTableName}")
    fun clear()
}

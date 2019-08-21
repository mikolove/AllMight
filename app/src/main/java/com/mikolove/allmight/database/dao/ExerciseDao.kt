package com.mikolove.allmight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.Entities.Exercise

@Dao
interface ExerciseDao{

    @Insert
    fun insert(exercise: Exercise)

    @Update
    fun update(exercise: Exercise)

    @Query("DELETE FROM ${AllmightDatabase.exerciseTableName}")
    fun clear()
}
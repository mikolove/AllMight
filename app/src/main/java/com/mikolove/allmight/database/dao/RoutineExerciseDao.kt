package com.mikolove.allmight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.Entities.RoutineExercise

@Dao
interface RoutineExerciseDao {

    @Insert
    fun insert(routineExercise: RoutineExercise)

    @Update
    fun update(routineExercise: RoutineExercise)

    @Query("DELETE FROM ${AllmightDatabase.routineExerciseTypeTableName}")
    fun clear()
}
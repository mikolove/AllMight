package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.RoutineExerciseSet
import com.mikolove.allmight.database.entities.RoutineExerciseWithExercise
import com.mikolove.allmight.database.entities.RoutineExerciseWithSet

@Dao
interface RoutineExerciseSetDao {

    @Insert
    fun insert(routineExerciseSet: RoutineExerciseSet) : Long

    @Update
    fun update(routineExerciseSet: RoutineExerciseSet)

    @Query("DELETE FROM ${AllmightDatabase.routineExerciseSetTableName}")
    fun clear()
}
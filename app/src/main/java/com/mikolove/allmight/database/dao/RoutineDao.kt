package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Routine
import com.mikolove.allmight.database.entities.RoutineWithWorkout

@Dao
interface RoutineDao{

    @Insert
    fun insert(routine: Routine) : Long

    @Update
    fun update(routine: Routine)

    @Query("DELETE FROM ${AllmightDatabase.routineTableName}")
    fun clear()

    @Query("DELETE FROM ${AllmightDatabase.routineTableName} WHERE id_routine = :id")
    fun clearById(id : Int)

    @Transaction
    @Query("SELECT * FROM ${AllmightDatabase.routineTableName} WHERE id_routine = :id_routine")
    fun getRoutineById(id_routine : Int) : LiveData<RoutineWithWorkout>

}
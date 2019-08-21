package com.mikolove.allmight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.Entities.Routine

@Dao
interface RoutineDao{

    @Insert
    fun insert(routine: Routine)

    @Update
    fun update(routine: Routine)

    @Query("DELETE FROM ${AllmightDatabase.routineTableName}")
    fun clear()
}
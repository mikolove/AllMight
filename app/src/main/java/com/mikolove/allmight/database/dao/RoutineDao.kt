package com.mikolove.allmight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Routine

@Dao
interface RoutineDao{

    @Insert
    fun insert(routine: Routine)

    @Update
    fun update(routine: Routine)

    @Query("DELETE FROM ${AllmightDatabase.routineTableName}")
    fun clear()

    @Query("DELETE FROM ${AllmightDatabase.routineTableName} WHERE id_routine = :id")
    fun clearById(id : Int)

    /*@Query("SELECT routineTable.id, routineTable.id_workout, routineTable.created_at, routineTable.created_at, routineTa" +
            " FROM ${AllmightDatabase.routineTableName} as routineTable, ${AllmightDatabase.workoutTableName} as workoutTable " +
            "WHERE routineTable.id_workout = workoutTable.id AND ")
    */

}
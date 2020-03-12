package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.RoutineExercise
import com.mikolove.allmight.database.entities.RoutineExerciseWithExercise
import com.mikolove.allmight.database.entities.RoutineExerciseWithSet

@Dao
interface RoutineExerciseDao {

    @Insert
    fun insert(routineExercise: RoutineExercise) : Long

    @Update
    fun update(routineExercise: RoutineExercise)

    @Query("DELETE FROM ${AllmightDatabase.routineExerciseTableName}")
    fun clear()

    @Transaction
    @Query("SELECT * FROM ${AllmightDatabase.routineExerciseTableName} WHERE id_routine = :id_routine")
    fun getRoutineExerciseWithExercises(id_routine: Int) : LiveData<List<RoutineExerciseWithExercise>>

    @Transaction
    @Query("SELECT * FROM ${AllmightDatabase.routineExerciseTableName} WHERE id_routine_exercise = :id_routine_exercise")
    fun getRoutineExerciseWithSet(id_routine_exercise : Int) : LiveData<RoutineExerciseWithSet>



}
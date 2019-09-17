package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Exercise

@Dao
interface ExerciseDao{

    @Insert
    fun insert(exercise: Exercise)

    @Update
    fun update(exercise: Exercise)

    @Query("DELETE FROM ${AllmightDatabase.exerciseTableName}")
    fun clearAll()

    @Query("DELETE FROM ${AllmightDatabase.exerciseTableName} WHERE id = :id")
    fun clearById(id : Int)

    @Query("SELECT * FROM ${AllmightDatabase.exerciseTableName} WHERE status = :status")
    fun getAllExercise(status : Boolean = true) : LiveData<List<Exercise>>

    @Query("SELECT * FROM ${AllmightDatabase.exerciseTableName} WHERE id = :id AND status = :status")
    fun getExerciseById(id : Int, status : Boolean = true) : LiveData<List<Exercise>>

    @Query("SELECT * FROM ${AllmightDatabase.exerciseTableName} WHERE status = :status AND id_workout_type = :id_workout_type")
    fun getAllExerciseByIdWorkoutType(id_workout_type : Int, status : Boolean = true) : LiveData<List<Exercise>>

}
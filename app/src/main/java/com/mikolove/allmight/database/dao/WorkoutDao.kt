package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Workout

@Dao
interface WorkoutDao {

    @Insert
    fun insert(workout: Workout) : Long

    @Update
    fun update(workout: Workout)

    @Query("DELETE FROM ${AllmightDatabase.workoutTableName}")
    fun clearAll()

    @Query("DELETE FROM ${AllmightDatabase.workoutTableName} WHERE id = :id")
    fun clearById(id : Int)

    @Query("SELECT * FROM ${AllmightDatabase.workoutTableName} ")
    fun getAllWorkout() : LiveData<List<Workout>>

    @Query("SELECT * FROM ${AllmightDatabase.workoutTableName} WHERE status = :status")
    fun getAllWorkout(status : Boolean = true) : LiveData<List<Workout>>

    @Query("SELECT * FROM ${AllmightDatabase.workoutTableName} WHERE id = :id AND status = :status")
    fun getWorkoutById(id : Int, status : Boolean = true) : LiveData<Workout>

    @Query("SELECT * FROM ${AllmightDatabase.workoutTableName} WHERE id_workout_type = :id_workout_type AND status = :status")
    fun getWorkoutByIdWorkoutType(id_workout_type : Int, status : Boolean = true) : LiveData<Workout>

}

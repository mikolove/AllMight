package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutWithExercises

@Dao
interface WorkoutDao {

    @Insert
    fun insert(workout: Workout) : Long

    @Update
    fun update(workout: Workout)

    @Query("DELETE FROM ${AllmightDatabase.workoutTableName}")
    fun clearAll()

    @Query("DELETE FROM ${AllmightDatabase.workoutTableName} WHERE id_workout = :id")
    fun clearById(id : Int)

    @Query("SELECT * FROM ${AllmightDatabase.workoutTableName} ")
    fun getAllWorkout() : LiveData<List<Workout>>

    @Query("SELECT * FROM ${AllmightDatabase.workoutTableName} WHERE status = :status")
    fun getAllWorkout(status : Boolean = true) : LiveData<List<Workout>>

    @Query("SELECT * FROM ${AllmightDatabase.workoutTableName} WHERE id_workout = :id AND status = :status")
    fun getWorkoutById(id : Int, status : Boolean = true) : LiveData<Workout>

    @Query("SELECT * FROM ${AllmightDatabase.workoutTableName} WHERE id_workout_type = :id_workout_type AND status = :status")
    fun getWorkoutByIdWorkoutType(id_workout_type : Int, status : Boolean = true) : LiveData<List<Workout>>

    @Transaction
    @Query("SELECT * FROM Workout WHERE id_workout = :id_workout")
    fun getWorkoutWithExercisesById(id_workout : Int) : LiveData<WorkoutWithExercises>


    @Transaction
    @Query("SELECT * FROM Workout")
    fun getWorkoutWithExercises() : LiveData<List<WorkoutWithExercises>>

    @Transaction
    @Query("SELECT * FROM Workout WHERE id_workout_type = :id_workout_type")
    fun getWorkoutWithExercisesByType(id_workout_type: Int) : LiveData<List<WorkoutWithExercises>>


}

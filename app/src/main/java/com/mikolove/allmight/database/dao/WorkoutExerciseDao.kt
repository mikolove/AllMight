package com.mikolove.allmight.database.dao

import androidx.room.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutExercise

@Dao
interface WorkoutExerciseDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workoutExercise: WorkoutExercise)

    @Update
    fun update(workoutExercise: WorkoutExercise)

    @Delete
    fun delete(workoutExercise: WorkoutExercise)

    @Query("DELETE FROM ${AllmightDatabase.workoutExerciseTableName} WHERE id_workout = :id_workout")
    fun deleteWorkoutLink(id_workout : Int)

    @Query("DELETE FROM ${AllmightDatabase.workoutExerciseTableName} WHERE id_exercise = :id_exercise")
    fun deleteExercise(id_exercise : Int)

    @Query("DELETE FROM ${AllmightDatabase.workoutExerciseTableName}")
    fun clear()
}

package com.mikolove.allmight.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.database.entities.Workout

@Dao
interface ExerciseDao{

    @Insert
    fun insert(exercise: Exercise) : Long

    @Update
    fun update(exercise: Exercise)

    @Query("DELETE FROM ${AllmightDatabase.exerciseTableName}")
    fun clearAll()

    @Query("DELETE FROM ${AllmightDatabase.exerciseTableName} WHERE id_exercise = :id")
    fun clearById(id : Int)

    @Query("SELECT * FROM ${AllmightDatabase.exerciseTableName} ")
    fun getAllExercise() : LiveData<List<Exercise>>

    @Query("SELECT * FROM ${AllmightDatabase.exerciseTableName} WHERE status = :status")
    fun getAllExercise(status : Boolean = true) : LiveData<List<Exercise>>

    @Query("SELECT * FROM ${AllmightDatabase.exerciseTableName} WHERE id_exercise = :id AND status = :status")
    fun getExerciseById(id : Int, status : Boolean = true) : LiveData<Exercise>

    @Query("SELECT * FROM ${AllmightDatabase.exerciseTableName} WHERE status = :status AND id_workout_type = :id_workout_type")
    fun getAllExerciseByIdWorkoutType(id_workout_type : Int, status : Boolean = true) : LiveData<List<Exercise>>

    @Query("SELECT e.id_exercise as id_exercise, e.name as name, wt.name as name_type, e.rep_count as rep_count, e.set_count as set_count, wt.id_workout_type as id_type , " +
            "( CASE WHEN we.id_exercise = e.id_exercise THEN 1 ELSE 0 END ) as is_selected " +
            "FROM ${AllmightDatabase.exerciseTableName} e " +
            "INNER JOIN ${AllmightDatabase.workoutTypeTableName} wt ON e.id_workout_type = wt.id_workout_type " +
            "LEFT OUTER JOIN ${AllmightDatabase.workoutExerciseTableName} we ON e.id_exercise = we.id_exercise " +
            "AND we.id_workout = :workout_id")
    fun getAllExerciseWorkout(workout_id: Int) : LiveData<List<AddExercise>>

    @Query("SELECT e.id_exercise as id_exercise, e.name as name, wt.name as name_type, e.rep_count as rep_count, e.set_count as set_count, wt.id_workout_type as id_type , " +
            "( CASE WHEN we.id_exercise = e.id_exercise THEN 1 ELSE 0 END ) as is_selected " +
            "FROM ${AllmightDatabase.exerciseTableName} e " +
            "INNER JOIN ${AllmightDatabase.workoutTypeTableName} wt ON e.id_workout_type = wt.id_workout_type " +
            "LEFT OUTER JOIN ${AllmightDatabase.workoutExerciseTableName} we ON e.id_exercise = we.id_exercise " +
            "WHERE is_selected = 1 "+
            "AND we.id_workout = :workout_id")
    fun getAllSelectedExerciseWorkout(workout_id : Int) : LiveData<List<AddExercise>>

}
package com.mikolove.allmight.repository

import androidx.lifecycle.LiveData
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.database.entities.WorkoutType

class ExerciseRepository (val dataSource : AllmightDatabase){


    fun getAllExercise() : LiveData<List<Exercise>> {
        return dataSource.exerciseDao().getAllExercise()
    }

    fun getAllExercise(status : Boolean) : LiveData<List<Exercise>> {
        return dataSource.exerciseDao().getAllExercise(status)
    }

    fun getExerciseById(id : Int) : LiveData<Exercise> {
        return dataSource.exerciseDao().getExerciseById(id)
    }

    fun getExerciseByWorkoutType(workoutType: WorkoutType, status : Boolean) : LiveData<List<Exercise>> {
        return dataSource.exerciseDao().getAllExerciseByIdWorkoutType(workoutType.id,status)
    }
    fun insert(exercise: Exercise) : Long{
        return dataSource.exerciseDao().insert(exercise)
    }

    fun update(exercise: Exercise){
        dataSource.exerciseDao().update(exercise)
    }

    //May Be Suspend call
    fun delete(exerciseId: Int){
        dataSource.exerciseDao().clearById(exerciseId)
    }



}
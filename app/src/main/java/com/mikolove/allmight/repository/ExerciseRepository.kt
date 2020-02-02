package com.mikolove.allmight.repository

import androidx.lifecycle.LiveData
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.database.entities.BasicElement
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.database.entities.WorkoutType
import timber.log.Timber

class ExerciseRepository (val dataSource : AllmightDatabase){


    fun getAllExercise() : LiveData<List<Exercise>> {
        return dataSource.exerciseDao().getAllExercise()
    }

    fun getAllExercise(status : Boolean) : LiveData<List<Exercise>> {
        return dataSource.exerciseDao().getAllExercise(status)
    }

    fun getExerciseById(id : Int, status: Boolean = true) : LiveData<Exercise> {
        return dataSource.exerciseDao().getExerciseById(id,status)
    }

    fun getExerciseByWorkoutType(id : Int, status : Boolean) : LiveData<List<Exercise>> {
        return dataSource.exerciseDao().getAllExerciseByIdWorkoutType(id,status)
    }

    fun getAddExercise(id : Int) : LiveData<List<AddExercise>>{
        return dataSource.exerciseDao().getAllExerciseWorkout(id)
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

    fun getMaxSeries() : List<BasicElement>{
        return (1..8).map { BasicElement(it,it.toString()) }
    }

    fun getMaxReps() : List<BasicElement>{
        return (1..15).map { BasicElement(it,it.toString()) }
    }

}
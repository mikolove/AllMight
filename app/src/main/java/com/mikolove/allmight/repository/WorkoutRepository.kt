package com.mikolove.allmight.repository

import androidx.lifecycle.LiveData
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutType

class WorkoutRepository (val dataSource : AllmightDatabase){


    fun getAllWorkout() : LiveData<List<Workout>> {
        return dataSource.workoutDao().getAllWorkout()
    }

    fun getAllWorkout(status : Boolean) : LiveData<List<Workout>> {
        return dataSource.workoutDao().getAllWorkout(status)
    }

    fun getWorkoutById(id : Int) : LiveData<Workout>{
        return dataSource.workoutDao().getWorkoutById(id)
    }

    fun getWorkoutByWorkoutType(id : Int, status : Boolean = true) : LiveData<List<Workout>>{
        return dataSource.workoutDao().getWorkoutByIdWorkoutType(id,status)
    }
    fun insert(workout: Workout) : Long{
        return dataSource.workoutDao().insert(workout)
    }

    fun update(workout: Workout){
        dataSource.workoutDao().update(workout)
    }

    //May Be Suspend call
    fun delete(workoutId: Int){
        dataSource.workoutDao().clearById(workoutId)
    }



}
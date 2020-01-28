package com.mikolove.allmight.repository

import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutType

class WorkoutTypeRepository (val dataSource : AllmightDatabase) {

    val workoutTypeList = dataSource.workoutTypeDao().getAllWorkoutType()

    fun insert(workoutType: WorkoutType){
        dataSource.workoutTypeDao().insert(workoutType)
    }

    fun delete(workoutType: WorkoutType){
        dataSource.workoutTypeDao().clearById(workoutType.id)
    }

    fun update(workoutType: WorkoutType){
        dataSource.workoutTypeDao().update(workoutType)
    }
}
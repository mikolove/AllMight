package com.mikolove.allmight.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mikolove.allmight.R
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutType

class WorkoutTypeRepository (val dataSource : AllmightDatabase, val application: Application) {

    val workoutTypeList = dataSource.workoutTypeDao().getAllWorkoutType()

    fun getWorkoutTypeFilter() : LiveData<List<WorkoutType>>{
        val result = MediatorLiveData<List<WorkoutType>>()
        result.addSource(workoutTypeList){ fromRoom ->
            result.value = listOf(WorkoutType(0 ,application.resources.getString(R.string.include_settings_chip_all)))
                .plus(fromRoom)
        }
        return result
    }

    fun insert(workoutType: WorkoutType){
        dataSource.workoutTypeDao().insert(workoutType)
    }

    fun delete(workoutType: WorkoutType){
        dataSource.workoutTypeDao().clearById(workoutType.id_workout_type)
    }

    fun update(workoutType: WorkoutType){
        dataSource.workoutTypeDao().update(workoutType)
    }
}
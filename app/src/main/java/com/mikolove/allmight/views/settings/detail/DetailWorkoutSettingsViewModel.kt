package com.mikolove.allmight.views.settings.detail

import android.app.Application
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutType
import com.mikolove.allmight.repository.WorkoutRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.*
import timber.log.Timber

class DetailWorkoutSettingsViewModel(val workoutId : Int = 0, dataSource: AllmightDatabase, application: Application) : ViewModel(){

    val database = dataSource

    private val wkTypeRepo = WorkoutTypeRepository(dataSource)
    private val wkRepo     = WorkoutRepository(dataSource)

    private var workout =  wkRepo.getWorkoutById(workoutId)
    fun getWorkout() = workout

    private val listWorkoutType =  wkTypeRepo.workoutTypeList
    fun getListWorkoutType() = listWorkoutType

    private val workoutType = MutableLiveData<BasicInfo>()
    fun getWorkoutType() = workoutType

    init{
        Timber.i("Workout %s WorkouList %s",workout.toString(),listWorkoutType.toString())
    }

    fun loadWorkoutType(){

        if(getWorkout().value?.id_workout_type == getWorkoutType().value?.getObjectId()) return

        listWorkoutType.value?.let {

            if(it.isEmpty()) return

            if(getWorkout().value?.id_workout_type == 0) workoutType.value = it.first()!!

            it.forEach {type ->
                if(type.id == getWorkout().value?.id_workout_type){
                    workoutType.value = type
                }
            }
        }
    }

    private suspend fun getWorkout(workoutId: Int) : LiveData<Workout>{
        return withContext(Dispatchers.IO){
            wkRepo.getWorkoutById(workoutId)
        }
    }

    private suspend fun getAllWorkoutType() : LiveData<List<WorkoutType>> {
        return withContext(Dispatchers.IO){
            wkTypeRepo.getListWorkoutType()
        }
    }

    fun onInsert(workout : Workout){
        viewModelScope.launch {
            insert(workout)
        }
    }

    private suspend fun insert(workout : Workout) {
        withContext(Dispatchers.IO) {
            wkRepo.insert(workout)
        }
    }

    fun onUpdate(workout : Workout){
        viewModelScope.launch {
            update(workout)
        }
    }

    private suspend fun update(workout : Workout) {
        withContext(Dispatchers.IO) {
            wkRepo.update(workout)
        }
    }
}

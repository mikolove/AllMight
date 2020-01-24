package com.mikolove.allmight.views.settings.detail

import android.app.Application
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutType
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.*

class DetailExerciseSettingsViewModel(val exerciseId : Int = 0, dataSource: AllmightDatabase, application: Application) : ViewModel(){

    val database = dataSource

    private val wkTypeRepo = WorkoutTypeRepository(dataSource)
    private val exRepo     = ExerciseRepository(dataSource)

    var exercise =  MediatorLiveData<Exercise>()

    private val _navigateToHomeSettings = MutableLiveData<Long>()
    val navigateToHomeSettings : LiveData<Long>
        get() = _navigateToHomeSettings

    private val listWorkoutType =  wkTypeRepo.workoutTypeList
    fun getListWorkoutType() = listWorkoutType

    private val workoutType = MutableLiveData<BasicInfo>()
    fun getWorkoutType() = workoutType

    init{

        exercise.addSource(exRepo.getExerciseById(exerciseId)) { fromRoom ->
            if(fromRoom == null){
                exercise.value = Exercise()
            }else{
                exercise.value = fromRoom
            }
        }
    }

    fun doneNavigatingToHomeSettings() {
        _navigateToHomeSettings.value = null
    }

    fun loadWorkoutType(){

        if(exercise.value?.id_workout_type == getWorkoutType().value?.getObjectId()) return

        listWorkoutType.value?.let {

            if(it.isEmpty()) return

            if(exercise.value?.id_workout_type == 0) workoutType.value = it.first()!!

            it.forEach {type ->
                if(type.id == exercise.value?.id_workout_type){
                    workoutType.value = type
                }
            }
        }
    }

    fun updateWorkoutType(){
        if(exercise.value != null && workoutType.value != null){
            exercise.value?.id_workout_type = workoutType.value?.getObjectId()!!
        }
    }

    private suspend fun getWorkout(exerciseId : Int) : LiveData<Exercise>{
        return withContext(Dispatchers.IO){
            exRepo.getExerciseById(exerciseId)
        }
    }

    private suspend fun getAllWorkoutType() : LiveData<List<WorkoutType>> {
        return withContext(Dispatchers.IO){
            wkTypeRepo.getListWorkoutType()
        }
    }

    fun insertExercise(){
        exercise.value?.let {
            viewModelScope.launch {
                val lastInsert = insert(it)
                lastInsert?.let {
                    _navigateToHomeSettings.value = it
                }
            }
        }
    }

    private suspend fun insert(exercise : Exercise) : Long{
        return withContext(Dispatchers.IO) {
           exRepo.insert(exercise)
        }
    }

    fun updateExercise(){
        exercise.value?.let {
            viewModelScope.launch {
                update(it)
                _navigateToHomeSettings.value = 1
            }
        }
    }

    private suspend fun update(exercise: Exercise) {
        withContext(Dispatchers.IO) {
            exRepo.update(exercise)
        }
    }

    fun deleteExercise(){
        exercise.value?.let{
            viewModelScope.launch {
                delete(it)
                _navigateToHomeSettings.value = 1
            }
        }
    }

    private suspend fun delete(exercise: Exercise) {
        withContext(Dispatchers.IO) {
            exRepo.delete(exercise.id)
        }
    }
}

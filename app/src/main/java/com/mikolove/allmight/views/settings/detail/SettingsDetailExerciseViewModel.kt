package com.mikolove.allmight.views.settings.detail

import android.app.Application
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.*
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.*

class SettingsDetailExerciseViewModel(private val exerciseId : Int = 0, private val name : String, private val status : Boolean = true, dataSource: AllmightDatabase, application: Application) : ViewModel(){

    val database = dataSource

    private val wkTypeRepo = WorkoutTypeRepository(dataSource,application)
    private val exRepo     = ExerciseRepository(dataSource)

    var exercise =  MediatorLiveData<Exercise>()

    private val _navigateToHomeSettings = MutableLiveData<Long>()
    val navigateToHomeSettings : LiveData<Long>
        get() = _navigateToHomeSettings


    //Spinner values
    private val listSet =  exRepo.getMaxSet()
    fun getListSet() = listSet

    private val setValue = MutableLiveData<BasicInfo>()
    fun getSetValue() = setValue

    private val listReps =  exRepo.getMaxReps()
    fun getListReps() = listReps

    private val repValue = MutableLiveData<BasicInfo>()
    fun getRepValue() = repValue

    private val listWorkoutType =  wkTypeRepo.workoutTypeList
    fun getListWorkoutType() = listWorkoutType

    private val workoutType = MutableLiveData<BasicInfo>()
    fun getWorkoutType() = workoutType

    init{
        exercise.addSource(exRepo.getExerciseById(exerciseId,status)) { fromRoom ->
            if(fromRoom == null){
                exercise.value = Exercise(name = name)
            }else{
                exercise.value = fromRoom
            }
        }
    }

    fun doneNavigatingToHomeSettings() {
        _navigateToHomeSettings.value = null
    }

    fun loadRep(){

        if(exercise.value?.rep_count == getRepValue().value?.getObjectId()) return

        listReps.let {

            if(it.isEmpty()) return

            if(exercise.value?.rep_count == 0) repValue.value = it.first()

            it.forEach {count ->
                if(count.id == exercise.value?.rep_count){
                    repValue.value = count
                }
            }
        }
    }

    fun loadSet(){

        if(exercise.value?.set_count == getSetValue().value?.getObjectId()) return

        listSet.let {

            if(it.isEmpty()) return

            if(exercise.value?.set_count == 0) setValue.value = it.first()

            it.forEach {count ->
                if(count.id == exercise.value?.set_count){
                    setValue.value = count
                }
            }
        }
    }


    fun loadWorkoutType(){

        if(exercise.value?.id_workout_type == getWorkoutType().value?.getObjectId()) return

        listWorkoutType.value?.let {

            if(it.isEmpty()) return

            if(exercise.value?.id_workout_type == 0) workoutType.value = it.first()

            it.forEach {type ->
                if(type.id_workout_type == exercise.value?.id_workout_type){
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

    fun updateRep(){
        if(exercise.value != null && repValue.value != null){
            exercise.value?.rep_count = repValue.value?.getObjectId()!!
        }
    }

    fun updateSet(){
        if(exercise.value != null && setValue.value != null){
            exercise.value?.set_count = setValue.value?.getObjectId()!!
        }
    }

    fun insertExercise(){
        exercise.value?.let {
            viewModelScope.launch {
                val lastInsert = insert(it)
                lastInsert.let {
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
            exRepo.delete(exercise.id_exercise)
        }
    }
}

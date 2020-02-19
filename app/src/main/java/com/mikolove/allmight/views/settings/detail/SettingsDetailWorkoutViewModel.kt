package com.mikolove.allmight.views.settings.detail

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.*
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.WorkoutRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.*

class SettingsDetailWorkoutViewModel(private val workoutId : Int = 0, private val name : String, private val status : Boolean = true, dataSource: AllmightDatabase, application: Application) : ViewModel(){

    val database = dataSource

    private val wkTypeRepo = WorkoutTypeRepository(dataSource,application)
    private val wkRepo     = WorkoutRepository(dataSource)
    private val exRepo     = ExerciseRepository(dataSource)

    var workoutWithExercise =  MediatorLiveData<WorkoutWithExercises>()
    var exercises = MediatorLiveData<List<AddExercise>>()

    private val _navigateToHomeSettings = MutableLiveData<Long>()
    val navigateToHomeSettings : LiveData<Long>
        get() = _navigateToHomeSettings

    private val listWorkoutType =  wkTypeRepo.workoutTypeList
    fun getListWorkoutType() = listWorkoutType

    private val workoutType = MutableLiveData<BasicInfo>()
    fun getWorkoutType() = workoutType

    init{

        workoutWithExercise.addSource(wkRepo.getWorkoutWithExercisesById(workoutId)) { fromRoom ->
            if(fromRoom == null){
                workoutWithExercise.value = WorkoutWithExercises()
            }else{
                workoutWithExercise.value = fromRoom
            }
        }
    }

    val listVisibility = MutableLiveData<Int>()
    val textVisibility = MutableLiveData<Int>()

    fun showAndHide(count : Int){
        if( count > 0){
            listVisibility.value = View.VISIBLE
            textVisibility.value = View.INVISIBLE
        }else{
            listVisibility.value = View.INVISIBLE
            textVisibility.value = View.VISIBLE
        }
    }
    private val _navigateToAdd = MutableLiveData<Int>()
    val navigateToAdd: LiveData<Int>
        get() = _navigateToAdd

    fun doneNavigatingToAdd() {
        _navigateToAdd.value = null
    }

    fun onAddClick(){
        _navigateToAdd.value = 0
    }

    fun doneNavigatingToHomeSettings() {
        _navigateToHomeSettings.value = null
    }


    fun loadWorkoutType(){

        if(workoutWithExercise.value?.workout_type?.id_workout_type == getWorkoutType().value?.getObjectId()) return

        listWorkoutType.value?.let {

            if(it.isEmpty()) return

            if(workoutWithExercise.value?.workout_type?.id_workout_type == 0) workoutType.value = it.first()

            it.forEach {type ->
                if(type.id_workout_type == workoutWithExercise.value?.workout_type?.id_workout_type){
                    workoutType.value = type
                }
            }
        }
    }

    fun updateWorkoutType(){
        if(workoutWithExercise.value?.workout != null && workoutType.value != null){
            workoutWithExercise.value?.workout?.id_workout_type = workoutType.value?.getObjectId()!!
        }
    }

    fun insertWorkout(){
        workoutWithExercise.value?.let {
            viewModelScope.launch {
                val lastInsert = insert(it.workout)
                lastInsert.let {
                    _navigateToHomeSettings.value = it
                }
            }
        }
    }

    private suspend fun insert(workout : Workout) : Long{
        return withContext(Dispatchers.IO) {
           wkRepo.insert(workout)
        }
    }

    fun updateWorkout(){
        workoutWithExercise.value?.let {
            viewModelScope.launch {
                update(it.workout)
                _navigateToHomeSettings.value = 1
            }
        }
    }

    private suspend fun update(workout : Workout) {
        withContext(Dispatchers.IO) {
            wkRepo.update(workout)
        }
    }

    fun deleteWorkout(){
        workoutWithExercise.value?.let{
            viewModelScope.launch {
                delete(it.workout)
                _navigateToHomeSettings.value = 1
            }
        }
    }

    private suspend fun delete(workout : Workout) {
        withContext(Dispatchers.IO) {
            wkRepo.delete(workout.id_workout)
        }
    }
}

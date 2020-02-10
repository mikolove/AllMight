package com.mikolove.allmight.views.settings.detail

import android.app.Application
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutType
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.WorkoutRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.*
import timber.log.Timber

class DetailWorkoutSettingsViewModel(private val workoutId : Int = 0, private val name : String, private val status : Boolean = true,  dataSource: AllmightDatabase, application: Application) : ViewModel(){

    val database = dataSource

    private val wkTypeRepo = WorkoutTypeRepository(dataSource,application)
    private val wkRepo     = WorkoutRepository(dataSource)
    private val exRepo     = ExerciseRepository(dataSource)

    var workout =  MediatorLiveData<Workout>()
    var exercises = MediatorLiveData<List<AddExercise>>()

    private val _navigateToHomeSettings = MutableLiveData<Long>()
    val navigateToHomeSettings : LiveData<Long>
        get() = _navigateToHomeSettings

    private val listWorkoutType =  wkTypeRepo.workoutTypeList
    fun getListWorkoutType() = listWorkoutType

    private val workoutType = MutableLiveData<BasicInfo>()
    fun getWorkoutType() = workoutType

    init{

        workout.addSource(wkRepo.getWorkoutById(workoutId,status)) { fromRoom ->
            if(fromRoom == null){
                workout.value = Workout(name =name)
            }else{
                workout.value = fromRoom
            }
        }

        exercises.addSource(exRepo.getSelectedAddExercise(workoutId)){ fromRoom ->
            if(fromRoom == null){
                exercises.value = mutableListOf<AddExercise>()
            }else{
                exercises.value = fromRoom
            }
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

        if(workout.value?.id_workout_type == getWorkoutType().value?.getObjectId()) return

        listWorkoutType.value?.let {

            if(it.isEmpty()) return

            if(workout.value?.id_workout_type == 0) workoutType.value = it.first()!!

            it.forEach {type ->
                if(type.id == workout.value?.id_workout_type){
                    workoutType.value = type
                }
            }
        }
    }

    fun updateWorkoutType(){
        if(workout.value != null && workoutType.value != null){
            workout.value?.id_workout_type = workoutType.value?.getObjectId()!!
        }
    }

    fun insertWorkout(){
        workout.value?.let {
            viewModelScope.launch {
                val lastInsert = insert(it)
                lastInsert?.let {
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
        workout.value?.let {
            viewModelScope.launch {
                update(it)
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
        workout.value?.let{
            viewModelScope.launch {
                delete(it)
                _navigateToHomeSettings.value = 1
            }
        }
    }

    private suspend fun delete(workout : Workout) {
        withContext(Dispatchers.IO) {
            wkRepo.delete(workout.id)
        }
    }
}

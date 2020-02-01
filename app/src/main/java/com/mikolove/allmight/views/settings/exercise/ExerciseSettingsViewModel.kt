package com.mikolove.allmight.views.settings.exercise

import android.app.Application
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExerciseSettingsViewModel (val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    private val wkRepo = ExerciseRepository(database)
    private val wkTpRepo = WorkoutTypeRepository(database,application)

    val listWorkoutType = wkTpRepo.getWorkoutTypeFilter()
    private val filterWkType = MutableLiveData<BasicInfo>()
    fun getFilterWkType() = filterWkType

    private val exerciseStatus = MutableLiveData<Boolean>()

    private val filterStatus = MutableLiveData<BasicInfo>()
    fun getFilterStatus() = filterStatus

    private val _filterChange = MutableLiveData<Int>()

    val exercises: LiveData<List<Exercise>>? = Transformations.switchMap(_filterChange) {
        var selected : Int = 0
        var status : Boolean = true
        filterWkType.value?.let{ item->
            selected = item.getObjectId()
        }
        exerciseStatus.value?.let { item ->
            status = item
        }
        when(selected){
            0 -> wkRepo.getAllExercise(status)
            else -> wkRepo.getExerciseByWorkoutType(selected,status)
        }
    }

    init {
        exerciseStatus.value = true
    }

    fun setFilterStatus(value : Boolean){
        exerciseStatus.value = value
    }

    private val _navigateToDetailExercise = MutableLiveData<Int>()
    val navigateToDetailExercise: LiveData<Int>
        get() = _navigateToDetailExercise

    fun doneNavigatingToDetailExercise() {
        _navigateToDetailExercise.value = null
    }

    fun onEditExerciseClick(workoutId : Int = 0){
        _navigateToDetailExercise.value = workoutId
    }

    fun onFilterChange(){
        _filterChange.value = 1
    }
    fun doneFilterChange(){
        _filterChange.value = null
    }
    fun deleteExercise(exerciseId : Int){
        exerciseId.let{
            viewModelScope.launch {
                delete(it)
            }
        }
    }

    private suspend fun delete(workoutId : Int) {
        withContext(Dispatchers.IO) {
            wkRepo.delete(workoutId)
        }
    }
}
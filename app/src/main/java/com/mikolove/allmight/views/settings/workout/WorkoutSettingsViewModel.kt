package com.mikolove.allmight.views.settings.workout

import android.app.Application
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutType
import com.mikolove.allmight.repository.WorkoutRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class WorkoutSettingsViewModel(val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    private val wkRepo = WorkoutRepository(database)
    private val wkTpRepo = WorkoutTypeRepository(database)

    val listWorkoutType = wkTpRepo.workoutTypeList

    private val filterWkType = MutableLiveData<BasicInfo>()
    fun getFilterWkType() = filterWkType

    private val filterStatus = MutableLiveData<BasicInfo>()
    fun getFilterStatus() = filterStatus

    private val _filterChange = MutableLiveData<Int>()

    val workouts: LiveData<List<Workout>>? = Transformations.switchMap(_filterChange) {
        filterWkType.value?.getObjectId()?.let {
                id -> wkRepo.getWorkoutByWorkoutType(id)
        }
    }

    private val _navigateToDetailWorkout = MutableLiveData<Int>()
    val navigateToDetailWorkout: LiveData<Int>
        get() = _navigateToDetailWorkout

    fun doneNavigatingToDetailWorkout() {
        Timber.i("Zob end Value set to null")
        _navigateToDetailWorkout.value = null
    }

    fun onEditWorkoutClick(workoutId : Int = 0){
        _navigateToDetailWorkout.value = workoutId
        Timber.i("Zob Value set to %d",_navigateToDetailWorkout.value)
    }

    fun onFilterChange(){
        _filterChange.value = 1
    }
    fun doneFilterChange(){
        _filterChange.value = null
    }
    fun deleteWorkout(workoutId : Int){
        workoutId.let{
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
package com.mikolove.allmight.views.settings.workout

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.repository.WorkoutRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class WorkoutSettingsViewModel(val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    private val wkRepo = WorkoutRepository(database)
    private val wkTpRepo = WorkoutTypeRepository(database,application)

    val listWorkoutType = wkTpRepo.getWorkoutTypeFilter()
    private val filterWkType = MutableLiveData<BasicInfo>()
    fun getFilterWkType() = filterWkType

    private val workoutStatus = MutableLiveData<Boolean>()

    private val filterStatus = MutableLiveData<BasicInfo>()
    fun getFilterStatus() = filterStatus

    private val _filterChange = MutableLiveData<Int>()

    val workouts: LiveData<List<Workout>>? = Transformations.switchMap(_filterChange) {
        var selected : Int = 0
        var status : Boolean = true
        filterWkType.value?.let{ item->
            selected = item.getObjectId()
        }
        workoutStatus.value?.let { item ->
            status = item
        }
        when(selected){
            0 -> wkRepo.getAllWorkout(status)
            else -> wkRepo.getWorkoutByWorkoutType(selected,status)
        }
    }

    init {
        workoutStatus.value = true
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
    fun setFilterStatus(value : Boolean){
        workoutStatus.value = value
    }

    private val _navigateToDetailWorkout = MutableLiveData<Int>()
    val navigateToDetailWorkout: LiveData<Int>
        get() = _navigateToDetailWorkout

    fun doneNavigatingToDetailWorkout() {
        _navigateToDetailWorkout.value = null
    }

    fun onEditWorkoutClick(workoutId : Int = 0){
        _navigateToDetailWorkout.value = workoutId
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
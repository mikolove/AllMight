package com.mikolove.allmight.views.home.choose

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.database.entities.Routine
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutWithExercises
import com.mikolove.allmight.repository.RoutineRepository
import com.mikolove.allmight.repository.WorkoutRepository
import com.mikolove.allmight.repository.WorkoutTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ChooseWorkoutViewModel(val database : AllmightDatabase, application: Application) : ViewModel() {

    private val wkRepo = WorkoutRepository(database)
    private val wkTpRepo = WorkoutTypeRepository(database,application)
    private val rtRepo = RoutineRepository(database)

    val listWorkoutType = wkTpRepo.getWorkoutTypeFilter()
    private val filterWkType = MutableLiveData<BasicInfo>()
    fun getFilterWkType() = filterWkType

    val listVisibility = MutableLiveData<Int>()
    val textVisibility = MutableLiveData<Int>()

    private val _navigateToRoutine = MutableLiveData<Long>()
    val navigateToRoutine : LiveData<Long>
        get() = _navigateToRoutine


    fun showAndHide(count : Int){
        if( count > 0){
            listVisibility.value = View.VISIBLE
            textVisibility.value = View.INVISIBLE
        }else{
            listVisibility.value = View.INVISIBLE
            textVisibility.value = View.VISIBLE
        }
    }

    private val _filterChange = MutableLiveData<Int>()

    fun onFilterChange(){
        _filterChange.value = 1
    }

    val workouts : LiveData<List<WorkoutWithExercises>>? = Transformations.switchMap(_filterChange) {
        var selected = 0
        filterWkType.value?.let{ item->
            selected = item.getObjectId()
        }
        when(selected){
            0 -> wkRepo.getWorkoutWithExercises()
            else -> wkRepo.getWorkoutWithExercisesByType(selected)
        }
    }

    fun createRoutine(id_workout : Int) {
        viewModelScope.launch {
            val routine = Routine(id_workout = id_workout, created_at = Date())
            _navigateToRoutine.value = insert(routine)
        }
    }

    suspend fun insert(routine : Routine) : Long{
        return withContext(Dispatchers.IO){
            rtRepo.insert(routine)
        }
    }
}
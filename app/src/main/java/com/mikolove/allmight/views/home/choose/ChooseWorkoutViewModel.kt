package com.mikolove.allmight.views.home.choose

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.*
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

    fun createRoutine(workoutWithExercise : WorkoutWithExercises) {
        viewModelScope.launch {
            //TODO : create Routine
            val routine = Routine(id_workout = workoutWithExercise.workout.id_workout, created_at = Date())
            val idRoutine = insertRoutine(routine)

            //TODO : create All Routine Exercise
            workoutWithExercise.exercises.forEach { exercise ->
                val routineExercise = RoutineExercise(nb_reps = exercise.rep_count,created_at = Date(),id_routine = idRoutine.toInt(),id_exercise = exercise.id_exercise)
                val idRoutineExercise = insertRoutineExercise(routineExercise)
                for(i in 1..exercise.set_count){
                    //TODO : create All Set By Exercise
                    val set = RoutineExerciseSet(id_set = i, reps = exercise.rep_count,weight = 0,created_at = Date(),id_routine_exercise = idRoutineExercise.toInt())
                    insertSet(set)
                }
            }
            _navigateToRoutine.value = idRoutine
        }
    }

    suspend fun insertRoutine(routine : Routine) : Long{
        return withContext(Dispatchers.IO){
            rtRepo.insert(routine)
        }
    }
    suspend fun insertRoutineExercise(routineExercise: RoutineExercise) : Long{
        return withContext(Dispatchers.IO){
            rtRepo.insertRoutineExercise(routineExercise)
        }
    }
    suspend fun insertSet(routineExerciseSet: RoutineExerciseSet) : Long{
        return withContext(Dispatchers.IO){
            rtRepo.insertRoutineExerciseSet(routineExerciseSet)
        }
    }

}
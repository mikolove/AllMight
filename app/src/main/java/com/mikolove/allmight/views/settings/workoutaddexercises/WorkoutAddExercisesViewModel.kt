package com.mikolove.allmight.views.settings.workoutaddexercises

import android.app.Application
import androidx.lifecycle.*
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.repository.ExerciseRepository
import com.mikolove.allmight.repository.WorkoutExerciseRepository
import com.mikolove.allmight.repository.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutAddExercisesViewModel(val workoutId : Int, val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    val exRepo = ExerciseRepository(database)
    val wkRepo = WorkoutRepository(database)
    val wkExRepo = WorkoutExerciseRepository(database)

    val workout = wkRepo.getWorkoutById(workoutId)

    private val _filterChange = MutableLiveData<Int>()
    val exercises : LiveData<List<AddExercise>>? = Transformations.switchMap(_filterChange) {
        var selected : Int = 0
        filterSelected.value?.let{ it->
            selected = it
        }
        when(selected){
            0 -> exRepo.getAddExercise(workoutId)
            else -> exRepo.getSelectedAddExercise(workoutId)
        }
    }

    val state = MutableLiveData<Boolean>()

    private val filterSelected = MutableLiveData<Int>()
    fun getFilterSelected() = filterSelected

    val blockAction = MutableLiveData<Boolean>()

    init {
        unlockAction()
        onFilterChange()
    }

    fun unlockAction(){
        blockAction.value = false
    }

    fun stateHasChange(){
        state.value = null
    }

    fun switchState(exercise : AddExercise){
        if(blockAction.value == true) return

        exercise?.let {
            if (it.is_selected == 0) it.is_selected = 1
            else it.is_selected = 0

            when(it.is_selected){
                1 -> addToworkout(it)
                0 -> deleteFromworkout(it)
            }
        }
    }

    private fun addToworkout(addExercise: AddExercise){
        addExercise?.let {
            blockAction.value = true
            viewModelScope.launch {
                insert(workoutId,addExercise.id_exercise)
                state.value = true

            }
        }
    }

    suspend fun insert(id_workout : Int, id_exercise : Int){
        withContext(Dispatchers.Default){
            wkExRepo.add(id_workout,id_exercise)
        }
    }

    private fun deleteFromworkout(addExercise: AddExercise){
        addExercise?.let {
            blockAction.value = true
            viewModelScope.launch {
                delete(workoutId,addExercise.id_exercise)
                state.value = true

            }
        }
    }

    suspend fun delete(id_workout: Int,id_exercise: Int){
        withContext(Dispatchers.Default){
            wkExRepo.delete(id_workout,id_exercise)
        }
    }

    fun setFilterStatus(value : Int){
        filterSelected.value = value
    }
    fun onFilterChange(){
        _filterChange.value = 1
    }
    fun doneFilterChange(){
        _filterChange.value = null
    }
}
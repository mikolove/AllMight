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
import timber.log.Timber

class WorkoutAddExercisesViewModel(val workoutId : Int, val database: AllmightDatabase, application: Application) : AndroidViewModel(application) {

    val exRepo = ExerciseRepository(database)
    val wkRepo = WorkoutRepository(database)
    val wkExRepo = WorkoutExerciseRepository(database)

    val workout = wkRepo.getWorkoutById(workoutId)

    private val filterSelected = MutableLiveData(0)

    fun setFilterStatus(value : Int){
        filterSelected.value = value
    }

    val state = MutableLiveData<Boolean>()

    val exercises : LiveData<List<AddExercise>> = Transformations.switchMap(filterSelected) {
        when(it ?: 0){
            0 -> exRepo.getAddExercise(workoutId)
            else -> exRepo.getSelectedAddExercise(workoutId)
        }
    }

    fun stateHasChange(){
        state.value = null
    }

    fun switchState(exercise : AddExercise){
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


}
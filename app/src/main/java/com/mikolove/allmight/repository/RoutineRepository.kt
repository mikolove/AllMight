package com.mikolove.allmight.repository

import androidx.lifecycle.LiveData
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.*

class RoutineRepository (val dataSource : AllmightDatabase){

    fun insert(routine : Routine) : Long{
        return dataSource.routineDao().insert(routine)
    }

    fun insertRoutineExercise(routineExercise : RoutineExercise) : Long{
        return dataSource.routineExerciseDao().insert(routineExercise)
    }

    fun insertRoutineExerciseSet(routineExerciseSet : RoutineExerciseSet) : Long{
        return dataSource.routineExerciseSetDao().insert(routineExerciseSet)
    }

    fun getRoutineExerciseWithExercise(id_routine : Int) : LiveData<List<RoutineExerciseWithExercise>>{
        return dataSource.routineExerciseDao().getRoutineExerciseWithExercises(id_routine)
    }

    fun getRoutineById(id_routine : Int) : LiveData<RoutineWithWorkout>{
        return dataSource.routineDao().getRoutineById(id_routine)
    }

    fun getRoutineExerciseWithSet(id_routine_exercise : Int) : LiveData<RoutineExerciseWithSet>{
        return dataSource.routineExerciseDao().getRoutineExerciseWithSet(id_routine_exercise)
    }

}
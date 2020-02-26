package com.mikolove.allmight.repository

import androidx.lifecycle.LiveData
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.Routine

class RoutineRepository (val dataSource : AllmightDatabase){

    fun insert(routine : Routine) : Long{
        return dataSource.routineDao().insert(routine)
    }

    fun getRoutineById(id_routine : Int) : LiveData<Routine>{
        return dataSource.routineDao().getRoutineById(id_routine)
    }
}
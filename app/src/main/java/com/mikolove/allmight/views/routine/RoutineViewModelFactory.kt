package com.mikolove.allmight.views.routine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikolove.allmight.database.AllmightDatabase

class RoutineViewModelFactory(private val dataSource: AllmightDatabase,private val id_routine : Int) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoutineViewModel::class.java)) {
            return RoutineViewModel(dataSource,id_routine) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
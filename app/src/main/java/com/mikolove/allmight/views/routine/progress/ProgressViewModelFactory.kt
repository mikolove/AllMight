package com.mikolove.allmight.views.routine.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikolove.allmight.database.AllmightDatabase

class ProgressViewModelFactory (private val dataSource: AllmightDatabase, private val id_routine : Int,private val id_exercise : Int) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProgressViewModel::class.java)) {
            return ProgressViewModel(dataSource,id_routine,id_exercise) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.mikolove.allmight.views.home.choose

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikolove.allmight.database.AllmightDatabase

class ChooseWorkoutViewModelFactory(private val dataSource: AllmightDatabase, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseWorkoutViewModel::class.java)) {
            return ChooseWorkoutViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
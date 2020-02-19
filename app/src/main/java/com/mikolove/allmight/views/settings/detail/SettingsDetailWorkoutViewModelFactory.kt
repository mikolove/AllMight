package com.mikolove.allmight.views.settings.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikolove.allmight.database.AllmightDatabase

class SettingsDetailWorkoutViewModelFactory (private val workoutId : Int = 0, private val dataSource: AllmightDatabase, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsDetailWorkoutViewModel::class.java)) {
            return SettingsDetailWorkoutViewModel(workoutId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


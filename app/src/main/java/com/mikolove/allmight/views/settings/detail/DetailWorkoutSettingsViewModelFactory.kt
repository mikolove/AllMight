package com.mikolove.allmight.views.settings.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikolove.allmight.database.AllmightDatabase

class DetailWorkoutSettingsViewModelFactory (private val workoutId : Int = 0, private val name : String, private val status : Boolean = true, private val dataSource: AllmightDatabase, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailWorkoutSettingsViewModel::class.java)) {
            return DetailWorkoutSettingsViewModel(workoutId, name, status, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


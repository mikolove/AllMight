package com.mikolove.allmight.views.settings.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikolove.allmight.database.AllmightDatabase

class DetailExerciseSettingsViewModelFactory (private val exerciseId : Int = 0, private val name : String, private val status : Boolean = true, private val dataSource: AllmightDatabase, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailExerciseSettingsViewModel::class.java)) {
            return DetailExerciseSettingsViewModel(exerciseId, name, status, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


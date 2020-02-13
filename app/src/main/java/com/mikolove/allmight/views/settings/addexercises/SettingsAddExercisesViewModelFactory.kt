package com.mikolove.allmight.views.settings.addexercises

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikolove.allmight.database.AllmightDatabase

class SettingsAddExercisesViewModelFactory(private val workoutId : Int, private val dataSource: AllmightDatabase, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsAddExercisesViewModel::class.java)) {
            return SettingsAddExercisesViewModel(workoutId,dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

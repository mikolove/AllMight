package com.mikolove.allmight.views.settings.exercise

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.dao.WorkoutDao
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.views.settings.workout.WorkoutSettingsViewModel

class ExerciseSettingsViewModelFactory(private val dataSource: AllmightDatabase, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseSettingsViewModel::class.java)) {
            return ExerciseSettingsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


package com.mikolove.allmight.views.settings.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikolove.allmight.database.AllmightDatabase
import timber.log.Timber

class HomeSettingsViewModel( val dataSource: AllmightDatabase, application: Application) : ViewModel(){


    private val _navigateToDetailWorkout = MutableLiveData<Int>()
    val navigateToDetailWorkout: LiveData<Int>
        get() = _navigateToDetailWorkout

    fun doneNavigatingToDetailWorkout() {
        Timber.i("Zob end Value set to null")
        _navigateToDetailWorkout.value = null
    }

    fun onAddWorkoutClick(){
        _navigateToDetailWorkout.value = 0
        Timber.i("Zob Value set to %d",_navigateToDetailWorkout.value)
    }

}
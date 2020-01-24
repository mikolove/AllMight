package com.mikolove.allmight.views.settings.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikolove.allmight.database.AllmightDatabase
import timber.log.Timber

class HomeSettingsViewModel( val dataSource: AllmightDatabase, application: Application) : ViewModel(){


    private val _navigateToDetails = MutableLiveData<Int>()
    val navigateToDetails: LiveData<Int>
        get() = _navigateToDetails

    fun doneNavigatingToDetails() {
        _navigateToDetails.value = null
    }

    fun onAddWorkoutClick(){
        _navigateToDetails.value = 0
    }

}
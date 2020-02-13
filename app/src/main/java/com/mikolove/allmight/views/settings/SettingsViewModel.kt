package com.mikolove.allmight.views.settings

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutType
import com.mikolove.allmight.repository.WorkoutTypeRepository
import timber.log.Timber

class SettingsViewModel(val dataSource: AllmightDatabase) : ViewModel(){

    private val _navigateToDetails = MutableLiveData<Int>()
    val navigateToDetails: LiveData<Int>
        get() = _navigateToDetails

    fun doneNavigatingToDetails() {
        _navigateToDetails.value = null
    }

    fun onAddClick(){
        _navigateToDetails.value = 0
    }

}
package com.mikolove.allmight.views.settings.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.mikolove.allmight.R

class HomeSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_settings)

        val navController = findNavController(R.id.home_settings_fragment)
        setupActionBarWithNavController(navController)
    }

}
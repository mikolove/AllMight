package com.mikolove.allmight.views.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.mikolove.allmight.R
import com.mikolove.allmight.views.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navController = findNavController(R.id.home_fragment)
        setupActionBarWithNavController(navController)

        //Bottom bar behaviour
        home_bottom_bar.replaceMenu(R.menu.home_menu)
        home_bottom_bar.setOnMenuItemClickListener {
            menuItem ->
                when(menuItem.itemId) {
                    R.id.home_menu_settings -> {
                        val intent = Intent(this,
                            SettingsActivity::class.java)
                        startActivity(intent)
                    }
                }
            true
        }

        home_bottom_fab.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToChooseWorkoutFragment())
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.home_fragment)
        return navController.navigateUp()
    }

}
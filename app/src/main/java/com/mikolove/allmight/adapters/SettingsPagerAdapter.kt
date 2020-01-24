package com.mikolove.allmight.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mikolove.allmight.views.settings.exercise.ExerciseSettingsFragment
import com.mikolove.allmight.views.settings.home.HomeSettingsFragment
import com.mikolove.allmight.views.settings.workout.WorkoutSettingsFragment
import timber.log.Timber
import java.lang.IllegalArgumentException

class SettingsPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        Timber.i("Position view pager %d",position)
       return when (position){
            0 -> WorkoutSettingsFragment()
            1 -> ExerciseSettingsFragment()
           else -> throw IllegalArgumentException()
       }
    }

    override fun getCount(): Int {
       return 2
    }

}
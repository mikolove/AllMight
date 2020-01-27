package com.mikolove.allmight.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mikolove.allmight.views.settings.exercise.ExerciseSettingsFragment
import com.mikolove.allmight.views.settings.workout.WorkoutSettingsFragment
import java.lang.IllegalArgumentException

class SettingsPagerAdapter(fragment : Fragment) :  FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> WorkoutSettingsFragment()
            1 -> ExerciseSettingsFragment()
            else -> throw IllegalArgumentException()
        }
    }
}

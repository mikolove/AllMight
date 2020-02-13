package com.mikolove.allmight.views.settings.workout

import android.view.View
import com.mikolove.allmight.database.entities.Workout

class SettingsWorkoutListener(val clickListener: (view : View, workout : Workout) -> Unit) {
    fun onClick(view : View, workout : Workout) = clickListener(view, workout)
}
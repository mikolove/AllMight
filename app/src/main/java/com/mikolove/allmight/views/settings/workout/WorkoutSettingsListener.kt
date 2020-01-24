package com.mikolove.allmight.views.settings.workout

import android.view.View
import com.mikolove.allmight.database.entities.Workout

class WorkoutSettingsListener(val clickListener: (view : View , workoutId: Int) -> Unit) {
    fun onClick(view : View, workout : Workout) = clickListener(view, workout.id)
}
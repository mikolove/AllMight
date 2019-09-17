package com.mikolove.allmight.views.settings.workout

import com.mikolove.allmight.database.entities.Workout

class WorkoutSettingsListener(val clickListener: (workoutId: Int) -> Unit) {
    fun onClick(workout : Workout) = clickListener(workout.id)
}
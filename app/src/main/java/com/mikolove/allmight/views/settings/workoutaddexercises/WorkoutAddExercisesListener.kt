package com.mikolove.allmight.views.settings.workoutaddexercises

import android.view.View
import com.mikolove.allmight.database.entities.Exercise

class WorkoutAddExercisesListener(val clickListener: (view : View, exercise: Exercise) -> Unit) {
    fun onClick(view : View, exercise : Exercise) = clickListener(view, exercise)
}
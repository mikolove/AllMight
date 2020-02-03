package com.mikolove.allmight.views.settings.workoutaddexercises

import android.view.View
import com.mikolove.allmight.database.entities.AddExercise

class WorkoutAddExercisesListener(val clickListener: (view : View, exercise: AddExercise) -> Unit) {
    fun onClick(view : View, exercise : AddExercise) = clickListener(view,exercise)
}
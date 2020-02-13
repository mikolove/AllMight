package com.mikolove.allmight.views.settings.addexercises

import android.view.View
import com.mikolove.allmight.database.entities.AddExercise

class SettingsAddExercisesListener(val clickListener: (view : View, exercise: AddExercise) -> Unit) {
    fun onClick(view : View, exercise : AddExercise) = clickListener(view,exercise)
}
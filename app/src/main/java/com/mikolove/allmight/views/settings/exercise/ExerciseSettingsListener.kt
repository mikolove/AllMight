package com.mikolove.allmight.views.settings.exercise

import android.view.View
import com.mikolove.allmight.database.entities.Exercise

class ExerciseSettingsListener(val clickListener: (view : View, exercise: Exercise) -> Unit) {
    fun onClick(view : View, exercise : Exercise) = clickListener(view, exercise)
}
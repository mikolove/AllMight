package com.mikolove.allmight.views.settings.exercise

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mikolove.allmight.database.entities.Exercise

class ExerciseSettingsAdapter(val clickExerciseListener : SettingsExerciseListener) : ListAdapter<Exercise, SettingsExerciseViewHolder>(ExerciseSettingDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsExerciseViewHolder {
        return SettingsExerciseViewHolder.from(parent)
    }

    override fun onBindViewHolder(holderExercise: SettingsExerciseViewHolder, position: Int) {
        val item = getItem(position)
        holderExercise.bind(clickExerciseListener,item)
    }

}

class ExerciseSettingDiffCallback : DiffUtil.ItemCallback<Exercise>() {
    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}

package com.mikolove.allmight.views.settings.exercise

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mikolove.allmight.database.entities.Exercise

class ExerciseSettingsAdapter(val clickListener : ExerciseSettingsListener) : ListAdapter<Exercise, ExerciseSettingsViewHolder>(ExerciseSettingDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseSettingsViewHolder {
        return ExerciseSettingsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ExerciseSettingsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,item)
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

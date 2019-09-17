package com.mikolove.allmight.views.settings.workout

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mikolove.allmight.database.entities.Workout

class WorkoutSettingsAdapter(val clickListener : WorkoutSettingsListener) : ListAdapter<Workout, WorkoutSettingsViewHolder>(WorkoutSettingDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutSettingsViewHolder {
        return WorkoutSettingsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WorkoutSettingsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,item)
    }

}

class WorkoutSettingDiffCallback : DiffUtil.ItemCallback<Workout>() {
    override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        return oldItem == newItem
    }
}

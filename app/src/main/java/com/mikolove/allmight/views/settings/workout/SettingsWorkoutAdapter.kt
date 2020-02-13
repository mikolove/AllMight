package com.mikolove.allmight.views.settings.workout

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mikolove.allmight.database.entities.Workout

class WorkoutSettingsAdapter(val clickWorkoutListener : SettingsWorkoutListener) : ListAdapter<Workout, SettingsWorkoutViewHolder>(WorkoutSettingDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsWorkoutViewHolder {
        return SettingsWorkoutViewHolder.from(parent)
    }

    override fun onBindViewHolder(holderWorkout: SettingsWorkoutViewHolder, position: Int) {
        val item = getItem(position)
        holderWorkout.bind(clickWorkoutListener,item)
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

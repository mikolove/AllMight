package com.mikolove.allmight.views.settings.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.databinding.ListItemWorkoutExerciseBinding

class WorkoutSettingsViewHolder private constructor(val binding: ListItemWorkoutExerciseBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: WorkoutSettingsListener, item: Workout) {
        binding.workout = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): WorkoutSettingsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemWorkoutExerciseBinding.inflate(layoutInflater, parent, false)

            return WorkoutSettingsViewHolder(binding)
        }
    }
}
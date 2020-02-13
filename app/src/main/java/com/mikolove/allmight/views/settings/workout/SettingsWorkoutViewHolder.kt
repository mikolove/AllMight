package com.mikolove.allmight.views.settings.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.databinding.ItemSettingsWorkoutBinding

class SettingsWorkoutViewHolder private constructor(val binding: ItemSettingsWorkoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickWorkoutListener: SettingsWorkoutListener, item: Workout) {
        binding.workout = item
        binding.clickListener = clickWorkoutListener
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): SettingsWorkoutViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemSettingsWorkoutBinding.inflate(layoutInflater, parent, false)

            return SettingsWorkoutViewHolder(binding)
        }
    }
}
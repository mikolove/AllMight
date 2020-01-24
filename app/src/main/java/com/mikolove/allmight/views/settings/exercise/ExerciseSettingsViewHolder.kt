package com.mikolove.allmight.views.settings.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.databinding.ListItemExerciseBinding

class ExerciseSettingsViewHolder private constructor(val binding: ListItemExerciseBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: ExerciseSettingsListener, item: Exercise) {
        binding.exercise = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): ExerciseSettingsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemExerciseBinding.inflate(layoutInflater, parent, false)

            return ExerciseSettingsViewHolder(binding)
        }
    }
}
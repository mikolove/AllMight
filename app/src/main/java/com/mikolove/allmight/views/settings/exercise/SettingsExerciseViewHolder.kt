package com.mikolove.allmight.views.settings.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.databinding.ItemSettingsExerciseBinding

class SettingsExerciseViewHolder private constructor(val binding: ItemSettingsExerciseBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickExerciseListener: SettingsExerciseListener, item: Exercise) {
        binding.exercise = item
        binding.clickListener = clickExerciseListener
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): SettingsExerciseViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemSettingsExerciseBinding.inflate(layoutInflater, parent, false)

            return SettingsExerciseViewHolder(binding)
        }
    }
}
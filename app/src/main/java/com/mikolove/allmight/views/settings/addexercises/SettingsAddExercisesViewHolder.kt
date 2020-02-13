package com.mikolove.allmight.views.settings.addexercises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.databinding.ItemSettingsAddExercisesBinding


class SettingsAddExercisesViewHolder private constructor(val binding: ItemSettingsAddExercisesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: SettingsAddExercisesListener, item: AddExercise) {
        binding.addExercise = item
        binding.listItemAeSelected.visibility = item.getVisibility()
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): SettingsAddExercisesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemSettingsAddExercisesBinding.inflate(layoutInflater, parent, false)

            return SettingsAddExercisesViewHolder(binding)
        }
    }
}
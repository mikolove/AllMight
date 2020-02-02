package com.mikolove.allmight.views.settings.workoutaddexercises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.AddExercise
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.databinding.FragmentWorkoutAddExercisesBinding
import com.mikolove.allmight.databinding.ListItemAddExerciseBinding

class WorkoutAddExercisesViewHolder private constructor(val binding: ListItemAddExerciseBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: WorkoutAddExercisesListener, item: AddExercise) {
        binding.addExercise = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): WorkoutAddExercisesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemAddExerciseBinding.inflate(layoutInflater, parent, false)

            return WorkoutAddExercisesViewHolder(binding)
        }
    }
}
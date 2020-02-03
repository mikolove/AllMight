package com.mikolove.allmight.views.settings.workoutaddexercises

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mikolove.allmight.database.entities.AddExercise
import timber.log.Timber

class WorkoutAddExercisesAdapter(val clickListener : WorkoutAddExercisesListener) : ListAdapter<AddExercise, WorkoutAddExercisesViewHolder>(WorkoutAddExerciseSettingDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutAddExercisesViewHolder {
        return WorkoutAddExercisesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WorkoutAddExercisesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,item)
    }

}

class WorkoutAddExerciseSettingDiffCallback : DiffUtil.ItemCallback<AddExercise>() {
    override fun areItemsTheSame(oldItem: AddExercise, newItem: AddExercise): Boolean {

        return oldItem.id_exercise == newItem.id_exercise
    }

    override fun areContentsTheSame(oldItem: AddExercise, newItem: AddExercise): Boolean {
        Timber.i("Old item %s New item%s",oldItem.toString(),newItem.toString())
        return oldItem == newItem
    }
}

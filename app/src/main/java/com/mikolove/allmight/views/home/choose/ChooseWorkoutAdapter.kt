package com.mikolove.allmight.views.home.choose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.Workout
import com.mikolove.allmight.database.entities.WorkoutWithExercises
import com.mikolove.allmight.databinding.ItemChooseWorkoutBinding

class ChooseWorkoutAdapter(private val clickWorkoutListener : ChooseWorkoutListener) : ListAdapter<WorkoutWithExercises, ChooseWorkoutViewHolder>(ChooseWorkoutDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseWorkoutViewHolder {
        return ChooseWorkoutViewHolder.from(parent)
    }

    override fun onBindViewHolder(holderWorkout: ChooseWorkoutViewHolder, position: Int) {
        val item = getItem(position)
        holderWorkout.bind(clickWorkoutListener,item)
    }

}

class ChooseWorkoutViewHolder(val binding : ItemChooseWorkoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickWorkoutListener: ChooseWorkoutListener, item: WorkoutWithExercises) {
        binding.workoutWithExercise = item
        binding.clickListener = clickWorkoutListener
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): ChooseWorkoutViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemChooseWorkoutBinding.inflate(layoutInflater, parent, false)

            return ChooseWorkoutViewHolder(binding)
        }
    }
}

class ChooseWorkoutListener(val clickListener: ( workout : WorkoutWithExercises) -> Unit) {
    fun onClick(workout : WorkoutWithExercises) = clickListener(workout)
}

class ChooseWorkoutDiffCallback : DiffUtil.ItemCallback<WorkoutWithExercises>() {
    override fun areItemsTheSame(oldItem: WorkoutWithExercises, newItem: WorkoutWithExercises): Boolean {
        return oldItem.workout.id_workout == newItem.workout.id_workout
    }

    override fun areContentsTheSame(oldItem: WorkoutWithExercises, newItem: WorkoutWithExercises): Boolean {
        return oldItem == newItem
    }
}

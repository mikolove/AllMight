package com.mikolove.allmight.views.routine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.databinding.ItemRoutineExercisesBinding

class RoutineAdapter(val clickListener: RoutineClickListener) : ListAdapter<Exercise,RoutineViewHolder>(RoutineExerciseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        return RoutineViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        holder.bind(clickListener,getItem(position))
    }
}
class RoutineViewHolder(val binding : ItemRoutineExercisesBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(clickListener: RoutineClickListener, item: Exercise) {
        binding.exercise = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object{
        fun from(parent : ViewGroup) : RoutineViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRoutineExercisesBinding.inflate(layoutInflater,parent,false)

            return RoutineViewHolder(binding)
        }
    }
}

class RoutineClickListener(val clickListener : (exercise : Exercise) -> Unit){
    fun onClick(exercise: Exercise) = clickListener(exercise)
}

class RoutineExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {
    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.id_exercise == newItem.id_exercise
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}

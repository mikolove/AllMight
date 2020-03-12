package com.mikolove.allmight.views.routine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.Exercise
import com.mikolove.allmight.database.entities.RoutineExerciseWithExercise
import com.mikolove.allmight.databinding.ItemRoutineExercisesBinding

class RoutineAdapter(val clickListener: RoutineClickListener) : ListAdapter<RoutineExerciseWithExercise,RoutineViewHolder>(RoutineExerciseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        return RoutineViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        holder.bind(clickListener,getItem(position))
    }
}
class RoutineViewHolder(val binding : ItemRoutineExercisesBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(clickListener: RoutineClickListener, item: RoutineExerciseWithExercise) {
        binding.routineWithRoutineExercise = item
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

class RoutineClickListener(val clickListener : (routineExerciseWithExercise : RoutineExerciseWithExercise) -> Unit){
    fun onClick(routineExerciseWithExercise: RoutineExerciseWithExercise) = clickListener(routineExerciseWithExercise)
}

class RoutineExerciseDiffCallback : DiffUtil.ItemCallback<RoutineExerciseWithExercise>() {
    override fun areItemsTheSame(oldItem: RoutineExerciseWithExercise, newItem: RoutineExerciseWithExercise): Boolean {
        return oldItem.routineExercise.id_routine_exercise == newItem.routineExercise.id_routine_exercise
    }

    override fun areContentsTheSame(oldItem: RoutineExerciseWithExercise, newItem: RoutineExerciseWithExercise): Boolean {
        return oldItem == newItem
    }
}

package com.mikolove.allmight.views.settings.addexercises

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mikolove.allmight.database.entities.AddExercise

class WorkoutAddExercisesAdapter(val clickListener : SettingsAddExercisesListener) : ListAdapter<AddExercise, SettingsAddExercisesViewHolder>(WorkoutAddExerciseSettingDiffCallback()), Filterable{

    //Init it later beacause submitList only notify if a new list is sended
    private lateinit var filteredData : MutableList<AddExercise>

    fun loadData(currentList : List<AddExercise>){
        filteredData = mutableListOf<AddExercise>()
        filteredData.addAll(currentList)
        submitList(filteredData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsAddExercisesViewHolder {
        return SettingsAddExercisesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SettingsAddExercisesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,item)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                submitList(filterResults.values as MutableList<AddExercise>)
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()
                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    filteredData
                else
                    filteredData.filter {
                        it.name.toLowerCase().contains(queryString) ||
                        it.name_type.toLowerCase().contains(queryString)
                    }
                return filterResults
                }
            }

        }

}

class WorkoutAddExerciseSettingDiffCallback : DiffUtil.ItemCallback<AddExercise>() {
    override fun areItemsTheSame(oldItem: AddExercise, newItem: AddExercise): Boolean {

        return oldItem.id_exercise == newItem.id_exercise
    }

    override fun areContentsTheSame(oldItem: AddExercise, newItem: AddExercise): Boolean {
        return oldItem == newItem
    }
}

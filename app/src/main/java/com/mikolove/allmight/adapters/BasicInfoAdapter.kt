package com.mikolove.allmight.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.databinding.ListItemBasicInfoBinding


class BasicInfoAdapter : ListAdapter<BasicInfo,BasicInfoViewHolder>(BasicInfoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicInfoViewHolder {
        return BasicInfoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BasicInfoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class BasicInfoViewHolder  private constructor (val binding: ListItemBasicInfoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind( item: BasicInfo) {
        binding.basicInfo = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): BasicInfoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBasicInfoBinding.inflate(layoutInflater, parent, false)
            return BasicInfoViewHolder(binding)
        }
    }
}

class BasicInfoDiffCallback : DiffUtil.ItemCallback<BasicInfo>() {
    override fun areItemsTheSame(oldItem: BasicInfo, newItem: BasicInfo): Boolean {

        return oldItem.getObjectId() == newItem.getObjectId()
    }

    override fun areContentsTheSame(oldItem: BasicInfo, newItem: BasicInfo): Boolean {
        return oldItem.getObjectName() == newItem.getObjectName()
    }
}
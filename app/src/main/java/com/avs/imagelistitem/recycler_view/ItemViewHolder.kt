package com.avs.imagelistitem.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avs.imagelistitem.UIData
import com.avs.imagelistitem.databinding.ListItemBinding

class ItemViewHolder private constructor(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        movieClickListener: ItemListener,
        item: UIData
    ) {
        binding.itemClickListener = movieClickListener
        binding.uiData = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return ItemViewHolder(binding)
        }
    }
}
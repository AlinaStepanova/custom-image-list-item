package com.avs.imagelistitem.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.avs.imagelistitem.UIData

class ItemDiffCallback : DiffUtil.ItemCallback<UIData>() {
    override fun areItemsTheSame(oldItem: UIData, newItem: UIData): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: UIData, newItem: UIData): Boolean {
        return oldItem == newItem
    }
}
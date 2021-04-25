package com.avs.imagelistitem.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.avs.imagelistitem.UIData

class ItemsAdapter(private val clickListener: ItemListener) :
    ListAdapter<UIData, ItemViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

}
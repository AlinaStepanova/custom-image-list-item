package com.avs.imagelistitem.recycler_view

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.avs.imagelistitem.UIData

class ItemsAdapter(private val clickListener: ItemListener, private val context: Context) :
    ListAdapter<UIData, ItemViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onViewRecycled(holder: ItemViewHolder) {
        holder.cleanup()
    }

}
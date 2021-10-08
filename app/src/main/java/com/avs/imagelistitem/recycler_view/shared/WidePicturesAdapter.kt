package com.avs.imagelistitem.recycler_view.shared

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avs.imagelistitem.UIData
import com.avs.imagelistitem.recycler_view.ItemDiffCallback
import com.avs.imagelistitem.recycler_view.ItemListener

class WidePicturesAdapter(private val clickListener: ItemListener, private val context: Context, private val isLandscape: Boolean = false) :
    ListAdapter<UIData, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is CityViewHolder) {
            holder.bind(clickListener, item, context)
        } else if (holder is LandscapeViewHolder) {
            holder.bind(clickListener, item, context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (isLandscape) {
            LandscapeViewHolder.from(parent)
        } else {
            CityViewHolder.from(parent)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is CityViewHolder) {
            holder.cleanup()
        } else if (holder is LandscapeViewHolder) {
            holder.cleanup()
        }
    }
}
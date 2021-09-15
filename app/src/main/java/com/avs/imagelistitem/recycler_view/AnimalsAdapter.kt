package com.avs.imagelistitem.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avs.imagelistitem.R
import com.avs.imagelistitem.UIData
import com.avs.imagelistitem.databinding.AnimalsListItemBinding
import com.squareup.picasso.Picasso

class AnimalsAdapter(private val clickListener: ItemListener) :
    ListAdapter<UIData, AnimalsAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    class ItemViewHolder private constructor(private val binding: AnimalsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movieClickListener: ItemListener,
            item: UIData
        ) {
            binding.itemClickListener = movieClickListener
            binding.uiData = item
            binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
                .toBuilder()
                .build()
            Picasso.get()
                .load(item.url)
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.ivPoster)
        }

        companion object {

            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AnimalsListItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }
}
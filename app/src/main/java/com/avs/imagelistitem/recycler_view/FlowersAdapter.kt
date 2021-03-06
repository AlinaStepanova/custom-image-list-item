package com.avs.imagelistitem.recycler_view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avs.imagelistitem.MAX_ALPHA
import com.avs.imagelistitem.R
import com.avs.imagelistitem.UIData
import com.avs.imagelistitem.databinding.ListItemFlowerBinding
import com.avs.imagelistitem.recycler_view.shared.ItemDiffCallback
import com.avs.imagelistitem.recycler_view.shared.ItemListener
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class FlowersAdapter(private val clickListener: ItemListener, private val context: Context) :
    ListAdapter<UIData, FlowersAdapter.ItemViewHolder>(ItemDiffCallback()) {

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

    class ItemViewHolder private constructor(private val binding: ListItemFlowerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var target: Target

        fun bind(
            movieClickListener: ItemListener,
            item: UIData,
            context: Context
        ) {
            binding.itemClickListener = movieClickListener
            binding.uiData = item
            target = initTarget(item, context)
            if (item.subTitle == null) {
                binding.tvSubTitle.visibility = View.GONE
            }
            binding.ivPoster.tag = target
            binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, CORNER_SIZE)
                .build()
            binding.vBackgroundColor.shapeAppearanceModel =
                binding.vBackgroundColor.shapeAppearanceModel
                    .toBuilder()
                    .setBottomLeftCorner(CornerFamily.ROUNDED, CORNER_SIZE)
                    .setBottomRightCorner(CornerFamily.ROUNDED, CORNER_SIZE)
                    .build()
            Picasso.get()
                .load(item.url)
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(target)
        }

        fun cleanup() {
            Picasso.get().cancelRequest(target)
            binding.ivPoster.setImageDrawable(null)
        }

        private fun initTarget(item: UIData, context: Context): Target {
            return object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    bitmap?.let {
                        binding.ivPoster.setImageBitmap(bitmap)
                        Palette.from(bitmap)
                            .generate { palette ->
                                palette?.let {
                                    val swatch = it.dominantSwatch
                                    binding.vBackgroundColor.setBackgroundColor(
                                        swatch?.rgb ?: getColorById(context, R.color.black)
                                    )
                                    if (swatch != null) {
                                        binding.tvTitle.setTextColor(
                                            ColorUtils.setAlphaComponent(swatch.titleTextColor, MAX_ALPHA)
                                        )
                                        binding.tvSubTitle.setTextColor(
                                            ColorUtils.setAlphaComponent(swatch.bodyTextColor, MAX_ALPHA)
                                        )
                                    } else {
                                        binding.tvTitle.setTextColor(
                                            getColorById(context, R.color.white)
                                        )
                                        binding.tvSubTitle.setTextColor(
                                            getColorById(context, R.color.white)
                                        )
                                    }
                                    binding.tvTitle.text = item.title
                                    binding.tvSubTitle.text = item.subTitle
                                }
                            }
                    }
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    binding.ivPoster.setImageDrawable(errorDrawable)
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    binding.ivPoster.setImageDrawable(placeHolderDrawable)
                }
            }
        }

        private fun getColorById(context: Context, id: Int) = ContextCompat.getColor(context, id)

        companion object {

            const val CORNER_SIZE = 100F

            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFlowerBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }
}
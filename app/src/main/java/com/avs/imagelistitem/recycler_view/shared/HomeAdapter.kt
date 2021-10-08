package com.avs.imagelistitem.recycler_view.shared

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avs.imagelistitem.MAX_ALPHA
import com.avs.imagelistitem.R
import com.avs.imagelistitem.UIData
import com.avs.imagelistitem.databinding.ListItemHomeBinding
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class HomeAdapter(private val clickListener: ItemListener, private val context: Context, private val isArtworks: Boolean = false) :
    ListAdapter<UIData, HomeAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item, position, context, isArtworks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onViewRecycled(holder: ItemViewHolder) {
        holder.cleanup()
    }

    class ItemViewHolder private constructor(private val binding: ListItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var target: Target

        fun bind(
            movieClickListener: ItemListener,
            item: UIData,
            position: Int,
            context: Context,
            isArtworks: Boolean
        ) {
            binding.itemClickListener = movieClickListener
            binding.uiData = item
            target = initTarget(item, context, isArtworks)
            binding.ivPoster.tag = target
            setUpImageShape(isArtworks, position)
            Picasso.get()
                .load(item.url)
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(target)
        }

        private fun setUpImageShape(isArtworks: Boolean, position: Int) {
            if (!isArtworks) {
                if (position % 2 == 0) {
                    binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
                        .toBuilder()
                        .setTopLeftCorner(CornerFamily.ROUNDED, CORNER_SIZE)
                        .setBottomRightCorner(CornerFamily.ROUNDED, CORNER_SIZE)
                        .build()
                    binding.vBackgroundColor.shapeAppearanceModel =
                        binding.vBackgroundColor.shapeAppearanceModel
                            .toBuilder()
                            .setBottomRightCorner(CornerFamily.ROUNDED, CORNER_SIZE)
                            .build()
                } else {
                    binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
                        .toBuilder()
                        .setTopRightCorner(CornerFamily.ROUNDED, CORNER_SIZE)
                        .setBottomLeftCorner(CornerFamily.ROUNDED, CORNER_SIZE)
                        .build()
                    binding.vBackgroundColor.shapeAppearanceModel =
                        binding.vBackgroundColor.shapeAppearanceModel
                            .toBuilder()
                            .setBottomLeftCorner(CornerFamily.ROUNDED, CORNER_SIZE)
                            .build()
                }
            }
        }

        fun cleanup() {
            Picasso.get().cancelRequest(target)
            binding.ivPoster.setImageDrawable(null)
        }

        private fun initTarget(item: UIData, context: Context, isArtworks: Boolean): Target {
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
                                    if (isArtworks) {
                                        binding.ivPoster.strokeColor = swatch?.rgb?.let { color ->
                                            ColorStateList.valueOf(color)
                                        }
                                        binding.ivPoster.strokeWidth = BORDER_SIZE
                                        binding.tvTitle.textSize = TEXT_SIZE
                                    }
                                    if (swatch != null) {
                                        binding.tvTitle.setTextColor(
                                            ColorUtils.setAlphaComponent(swatch.titleTextColor, MAX_ALPHA)
                                        )
                                    } else {
                                        binding.tvTitle.setTextColor(
                                            getColorById(context, R.color.white)
                                        )
                                    }
                                    binding.tvTitle.text = item.title
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

            const val CORNER_SIZE = 75F
            const val BORDER_SIZE = 24F
            const val TEXT_SIZE = 20F

            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemHomeBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<UIData>() {
    override fun areItemsTheSame(oldItem: UIData, newItem: UIData) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: UIData, newItem: UIData) = oldItem == newItem
}

class ItemListener(val clickListener: (uiData: UIData) -> Unit) {
    fun onClick(uiData: UIData) = clickListener(uiData)
}
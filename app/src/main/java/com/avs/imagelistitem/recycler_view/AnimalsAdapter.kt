package com.avs.imagelistitem.recycler_view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.databinding.ViewDataBinding
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avs.imagelistitem.R
import com.avs.imagelistitem.UIData
import com.avs.imagelistitem.databinding.AnimalsListItemLeftBinding
import com.avs.imagelistitem.databinding.AnimalsListItemRightBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class AnimalsAdapter(private val clickListener: ItemListener, private val context: Context) :
    ListAdapter<UIData, AnimalsAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item, context)
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return if (viewType == 0) {
            ItemViewHolder.fromLeft(parent)
        } else {
            ItemViewHolder.fromRight(parent)
        }
    }

    override fun onViewRecycled(holder: ItemViewHolder) {
        holder.cleanup()
    }

    class ItemViewHolder private constructor(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var target: Target

        fun bind(
            movieClickListener: ItemListener,
            item: UIData,
            context: Context
        ) {
            if (binding is AnimalsListItemLeftBinding) {
                binding.itemClickListener = movieClickListener
                binding.uiData = item
                target = initTarget(item, context)
                binding.ivPoster.tag = target
                binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
                    .toBuilder()
                    .build()
                loadImage(item)
            } else {
                binding as AnimalsListItemRightBinding
                binding.itemClickListener = movieClickListener
                binding.uiData = item
                target = initTarget(item, context)
                binding.ivPoster.tag = target
                binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
                    .toBuilder()
                    .build()
                loadImage(item)
            }
        }

        private fun loadImage(item: UIData) {
            Picasso.get()
                .load(item.url)
                .resize(CIRCLE_SIZE, CIRCLE_SIZE)
                .centerCrop(Gravity.TOP)
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(target)
        }

        fun cleanup() {
            Picasso.get().cancelRequest(target)
            if (binding is AnimalsListItemLeftBinding) {
                binding.ivPoster.setImageDrawable(null)
            } else {
                binding as AnimalsListItemRightBinding
                binding.ivPoster.setImageDrawable(null)
            }
        }

        private fun initTarget(item: UIData, context: Context): Target {
            return object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    bitmap?.let {
                        if (binding is AnimalsListItemLeftBinding) {
                            binding.ivPoster.setImageBitmap(bitmap)
                            Palette.from(bitmap)
                                .generate { palette ->
                                    palette?.let {
                                        val swatch = it.dominantSwatch
                                        binding.idContainer.setBackgroundColor(
                                            swatch?.rgb ?: getColorById(context, R.color.black)
                                        )
                                        if (swatch != null) {
                                            binding.tvTitle.setTextColor(
                                                ColorUtils.setAlphaComponent(
                                                    swatch.titleTextColor,
                                                    255
                                                )
                                            )
                                        } else {
                                            binding.tvTitle.setTextColor(
                                                getColorById(
                                                    context,
                                                    R.color.white
                                                )
                                            )
                                        }
                                        binding.tvTitle.text = item.title
                                    }
                                }
                        } else {
                            binding as AnimalsListItemRightBinding
                            binding.ivPoster.setImageBitmap(bitmap)
                            Palette.from(bitmap)
                                .generate { palette ->
                                    palette?.let {
                                        val swatch = it.dominantSwatch
                                        binding.idContainer.setBackgroundColor(
                                            swatch?.rgb ?: getColorById(context, R.color.black)
                                        )
                                        if (swatch != null) {
                                            binding.tvTitle.setTextColor(
                                                ColorUtils.setAlphaComponent(
                                                    swatch.titleTextColor,
                                                    255
                                                )
                                            )
                                        } else {
                                            binding.tvTitle.setTextColor(
                                                getColorById(
                                                    context,
                                                    R.color.white
                                                )
                                            )
                                        }
                                        binding.tvTitle.text = item.title
                                    }
                                }
                        }
                    }
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    if (binding is AnimalsListItemLeftBinding) {
                        binding.ivPoster.setImageDrawable(errorDrawable)
                    } else {
                        binding as AnimalsListItemRightBinding
                        binding.ivPoster.setImageDrawable(errorDrawable)
                    }
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    if (binding is AnimalsListItemLeftBinding) {
                        binding.ivPoster.setImageDrawable(placeHolderDrawable)
                    } else {
                        binding as AnimalsListItemRightBinding
                        binding.ivPoster.setImageDrawable(placeHolderDrawable)
                    }
                }
            }
        }

        private fun getColorById(context: Context, id: Int) = ContextCompat.getColor(context, id)

        companion object {

            const val CIRCLE_SIZE = 480

            fun fromLeft(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AnimalsListItemLeftBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }

            fun fromRight(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AnimalsListItemRightBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }
}
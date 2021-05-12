package com.avs.imagelistitem.recycler_view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.squareup.picasso.Target
import androidx.recyclerview.widget.RecyclerView
import com.avs.imagelistitem.R
import com.avs.imagelistitem.UIData
import com.avs.imagelistitem.databinding.ListItemBinding
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso

class ItemViewHolder private constructor(private val binding: ListItemBinding) :
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
        binding.ivPoster.tag = target
        binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, 100F)
            //.setAllCornerSizes(150F)
            .build()
        binding.vBackgroundColor.shapeAppearanceModel =
            binding.vBackgroundColor.shapeAppearanceModel
                .toBuilder()
                .setBottomLeftCorner(CornerFamily.ROUNDED, 100F)
                .setBottomRightCorner(CornerFamily.ROUNDED, 100F)
                .build()
        Picasso.get()
            .load(item.url)
            .into(target)
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
                                        ColorUtils.setAlphaComponent(swatch.titleTextColor, 255)
                                    )
                                    binding.tvSubTitle.setTextColor(
                                        ColorUtils.setAlphaComponent(swatch.bodyTextColor, 255)
                                    )
                                } else {
                                    binding.tvTitle.setTextColor(
                                        getColorById(
                                            context,
                                            R.color.white
                                        )
                                    )
                                    binding.tvSubTitle.setTextColor(
                                        getColorById(
                                            context,
                                            R.color.white
                                        )
                                    )
                                }
                                binding.tvTitle.text = item.title
                                binding.tvSubTitle.text = item.subTitle
                            }
                        }
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        }
    }

    private fun getColorById(context: Context, id: Int) = ContextCompat.getColor(context, id)

    companion object {
        fun from(parent: ViewGroup): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return ItemViewHolder(binding)
        }
    }
}
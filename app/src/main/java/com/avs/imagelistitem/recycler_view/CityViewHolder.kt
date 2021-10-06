package com.avs.imagelistitem.recycler_view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.avs.imagelistitem.MAX_ALPHA
import com.avs.imagelistitem.R
import com.avs.imagelistitem.UIData
import com.avs.imagelistitem.databinding.ListItemCityBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class CityViewHolder private constructor(private val binding: ListItemCityBinding) :
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

        fun from(parent: ViewGroup): CityViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemCityBinding.inflate(layoutInflater, parent, false)
            return CityViewHolder(binding)
        }
    }
}

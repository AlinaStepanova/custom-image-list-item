package com.avs.imagelistitem.recycler_view.shared

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
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
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import androidx.annotation.ColorInt
import com.avs.imagelistitem.databinding.ListItemLandscapeBinding
import com.avs.imagelistitem.recycler_view.ItemListener
import kotlin.math.roundToInt


class LandscapeViewHolder private constructor(private val binding: ListItemLandscapeBinding) :
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
                                val colorWithAlpha = adjustAlpha(swatch?.rgb ?: getColorById(context, R.color.white), ALPHA_VALUE)
                                binding.tvTitle.background.setTint(colorWithAlpha)
                                if (swatch != null) {
                                    binding.tvTitle.setTextColor(
                                        ColorUtils.setAlphaComponent(swatch.titleTextColor, MAX_ALPHA)
                                    )
                                } else {
                                    binding.tvTitle.setTextColor(
                                        getColorById(context, R.color.black)
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

    @ColorInt
    fun adjustAlpha(@ColorInt color: Int, factor: Float): Int {
        val alpha = (Color.alpha(color) * factor).roundToInt()
        val red: Int = Color.red(color)
        val green: Int = Color.green(color)
        val blue: Int = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    companion object {

        const val ALPHA_VALUE = 0.75F

        fun from(parent: ViewGroup): LandscapeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemLandscapeBinding.inflate(layoutInflater, parent, false)
            return LandscapeViewHolder(binding)
        }
    }
}
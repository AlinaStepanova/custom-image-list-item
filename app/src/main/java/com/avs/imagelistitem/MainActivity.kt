package com.avs.imagelistitem

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import com.avs.imagelistitem.databinding.ActivityMainBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CornerSize
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import jp.wasabeef.picasso.transformations.CropTransformation

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    //private val target = initTarget()
    val list: ArrayList<UIData> = ArrayList(8)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val widthRatio = 1F
//        val heightRatio = 1F
//        list.add(UIData(url, "Title 1", "Some description 1"))
//        list.add(UIData(url2, "Title 2", "Some description 2"))
//        list.add(UIData(url3, "Title 3", "Some description 3"))
//        list.add(UIData(url4, "Title 4", "Some description 4"))
//        list.add(UIData(url5, "Title 5", "Some description 5"))
//        list.add(UIData(url6, "Title 5", "Some description 6"))
//        list.add(UIData(url7, "Title 7", "Some description 7"))
//        binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
//            .toBuilder()
//            .setAllCorners(CornerFamily.ROUNDED, 100F)
//            //.setAllCornerSizes(150F)
//            .build()
//        binding.vBackgroundColor.shapeAppearanceModel =
//            binding.vBackgroundColor.shapeAppearanceModel
//                .toBuilder()
//                .setBottomLeftCorner(CornerFamily.ROUNDED, 100F)
//                .setBottomRightCorner(CornerFamily.ROUNDED, 100F)
//                .build()
//        Picasso.get()
//            .load(list[0].url)
//            .transform(
//                CropTransformation(
//                    widthRatio,
//                    heightRatio,
//                    CropTransformation.GravityHorizontal.CENTER,
//                    CropTransformation.GravityVertical.CENTER
//                )
//            )
//            .into(target)
//        binding.ivPoster.tag = target
    }

//    private fun initTarget(): Target {
//        return object : Target {
//            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
//                bitmap?.let {
//                    binding.ivPoster.setImageBitmap(bitmap)
//                    Palette.from(bitmap)
//                        .generate { palette ->
//                            palette?.let {
//                                val swatch = it.dominantSwatch
//                                binding.vBackgroundColor.setBackgroundColor(
//                                    swatch?.rgb ?: getColorById(R.color.black)
//                                )
//                                if (swatch != null) {
//                                    binding.tvTitle.setTextColor(
//                                        ColorUtils.setAlphaComponent(swatch.titleTextColor, 255)
//                                    )
//                                    binding.tvSubTitle.setTextColor(
//                                        ColorUtils.setAlphaComponent(swatch.bodyTextColor, 255)
//                                    )
//                                } else {
//                                    binding.tvTitle.setTextColor(getColorById(R.color.white))
//                                    binding.tvSubTitle.setTextColor(getColorById(R.color.white))
//                                }
//                                binding.tvTitle.text = list[0].title
//                                binding.tvSubTitle.text = list[0].subTitle
//                            }
//                        }
//                }
//            }
//
//            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
//
//            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
//        }
//    }

    private fun getColorById(id: Int) = ContextCompat.getColor(this, id)

//    override fun onDestroy() {
//        Picasso.get().cancelRequest(target)
//        super.onDestroy()
//    }
}
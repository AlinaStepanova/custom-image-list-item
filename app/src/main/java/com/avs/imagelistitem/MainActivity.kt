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
    private val target = initTarget()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val widthRatio = 1F
        val heightRatio = 1F
        val url =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Nelumno_nucifera_open_flower_-_botanic_garden_adelaide2.jpg/1200px-Nelumno_nucifera_open_flower_-_botanic_garden_adelaide2.jpg"
        val url2 = "https://s3.amazonaws.com/cdn.brecks.com/images/800/73689A.jpg"
        val url3 =
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/asiatic-lily-cappuccino-lily-creative-commons_11653.jpg"
        val url4 =
            "https://imagesvc.meredithcorp.io/v3/mm/image?q=85&c=sc&poi=face&w=2000&h=1000&url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F34%2F2021%2F02%2F18%2Flily-flower-gardeing-getty-0221-2000.jpg"
        val url5 =
            "https://carithers.imgix.net/images/itemVariation/RosesandStargazers-Final4-21012035954.jpg"
        val url6 = "https://flowersadvice.ru/wp-content/uploads/2018/04/fialka-bolezni.jpg"
        val url7 =
            "https://www.bloomsbythebox.com/img/product/xlarge/01747a__rose_blue_vendela_60cm.jpg"
        val url8 = "https://www.koziel.fr/18894-pdt_1500/wall-of-roses-mural.jpg"
        val url9 = "https://data.whicdn.com/images/341475698/original.jpg"
        val url10 = "https://papers.co/wallpaper/papers.co-mq18-red-flower-spring-fun-nature-6-wallpaper.jpg"
        binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.CUT, 100F)
            //.setAllCornerSizes(150F)
            .build()
        binding.vColor.shapeAppearanceModel = binding.vColor.shapeAppearanceModel
            .toBuilder()
            .setBottomLeftCorner(CornerFamily.CUT, 100F)
            .setBottomRightCorner(CornerFamily.CUT, 100F)
            .build()
        Picasso.get()
            .load(url9)
            .transform(
                CropTransformation(
                    widthRatio,
                    heightRatio,
                    CropTransformation.GravityHorizontal.CENTER,
                    CropTransformation.GravityVertical.CENTER
                )
            )
            .into(target)
        binding.ivPoster.tag = target
    }

    private fun initTarget(): Target {
        return object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap?.let {
                    binding.ivPoster.setImageBitmap(bitmap)
                    Palette.from(bitmap)
                        .generate { palette ->
                            palette?.let {
                                val swatch = it.dominantSwatch
                                binding.vColor.setBackgroundColor(
                                    swatch?.rgb ?: getColorById(R.color.black)
                                )
                                if (swatch != null) {
                                    binding.tvTitle.setTextColor(
                                        ColorUtils.setAlphaComponent(swatch.titleTextColor, 255)
                                    )
                                } else {
                                    binding.tvTitle.setTextColor(getColorById(R.color.white))
                                }
                            }
                        }
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        }
    }

    private fun getColorById(id: Int) = ContextCompat.getColor(this, id)

    override fun onDestroy() {
        Picasso.get().cancelRequest(target)
        super.onDestroy()
    }
}
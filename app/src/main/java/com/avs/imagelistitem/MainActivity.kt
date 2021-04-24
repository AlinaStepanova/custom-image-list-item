package com.avs.imagelistitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.avs.imagelistitem.databinding.ActivityMainBinding
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropTransformation

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val widthRatio = 1F
        val heightRatio = 1F
        val url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Nelumno_nucifera_open_flower_-_botanic_garden_adelaide2.jpg/1200px-Nelumno_nucifera_open_flower_-_botanic_garden_adelaide2.jpg"
        binding.ivPoster.shapeAppearanceModel = binding.ivPoster.shapeAppearanceModel
                .toBuilder()
                .build()
        Picasso.get()
                .load(url)
                .transform(
                        CropTransformation(
                                widthRatio,
                                heightRatio,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.TOP
                        )
                )
                .into(binding.ivPoster)
    }
}
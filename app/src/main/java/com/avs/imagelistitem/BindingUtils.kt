

package com.avs.imagelistitem

import android.content.res.Configuration
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropTransformation
import jp.wasabeef.picasso.transformations.CropTransformation.GravityHorizontal
import jp.wasabeef.picasso.transformations.CropTransformation.GravityVertical


@BindingAdapter("posterImage")
fun ShapeableImageView.setPosterImage(item: UIData) {
    val widthRatio = 1F
    val pixels = 100
    var heightRatio = 0.65F


    Picasso.get()
        .load(item.url)
        .transform(
            CropTransformation(
                widthRatio,
                heightRatio,
                GravityHorizontal.CENTER,
                GravityVertical.TOP
            )
        )
        .into(this)
}
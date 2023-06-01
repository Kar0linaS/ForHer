package com.dziubi.forher.data

import androidx.annotation.DrawableRes
import com.dziubi.forher.R

data class Product(
    val id :Int,
    val name : String,
    val ingredients: String,
    val desc: String,
    val price: Float,
    @DrawableRes val image: Int = R.drawable.profile_image

)

package com.example.knownnyc.domain.models

import androidx.annotation.DrawableRes

data class Borough(
    val boroCode: Char,
    val name: String,
    val longName: String,
    @DrawableRes val image: Int,
)

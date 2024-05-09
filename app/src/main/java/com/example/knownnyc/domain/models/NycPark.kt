package com.example.knownnyc.domain.models

data class NycPark(

    val signname: String,
    val location: String,
    val waterfront: Boolean = false,
    val url: String,

)

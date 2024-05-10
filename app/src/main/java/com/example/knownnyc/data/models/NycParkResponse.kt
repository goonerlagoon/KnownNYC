package com.example.knownnyc.data.models

import kotlinx.serialization.Serializable

@Serializable
data class NycParkResponse(
    val signname: String?,
    val location: String?,
    val waterfront: Boolean = false,
    val url: String?,
 )

//TODO: Project 2

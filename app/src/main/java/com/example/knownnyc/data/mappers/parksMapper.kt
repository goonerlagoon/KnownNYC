package com.example.knownnyc.data.mappers

import com.example.knownnyc.data.models.NycParkResponse
import com.example.knownnyc.domain.models.NycPark


//TODO: Project 2

fun parksMapper(parksList: List<NycParkResponse>): List<NycPark> {

    return parksList.filter {

        !it.signname.isNullOrEmpty() && !it.location.isNullOrEmpty() && !it.url.isNullOrEmpty()
    }.map { validResponse ->

        NycPark(
            signname = validResponse.signname!!,
            location = validResponse.location!!,
            waterfront = validResponse.waterfront,
            url = validResponse.url!!
        )
    }

}
package com.example.knownnyc.data.mappers

import com.example.knownnyc.data.models.NycParkResponse
import com.example.knownnyc.domain.models.NycPark


//TODO: Project 2

fun parksMapper(parksList: List<NycParkResponse>): List<NycPark> {

    val validParks = mutableListOf<NycPark>()

    parksList.forEach { park ->
        if (!park.signname.isNullOrEmpty() && !park.location.isNullOrEmpty() && !park.url.isNullOrEmpty()) {
            val park = NycPark(
                signname = park.signname,
                location = park.location,
                waterfront = park.waterfront,
                url = park.url
            )

            validParks.add(park)
        }
    }

    return validParks
}

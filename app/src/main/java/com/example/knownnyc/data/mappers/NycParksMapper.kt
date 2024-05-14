package com.example.knownnyc.data.mappers

import com.example.knownnyc.data.models.NycParkResponse
import com.example.knownnyc.domain.models.NycPark


//TODO: Project

fun nycParksMapper(parksList: List<NycParkResponse>): List<NycPark> {

    val filteredParks = mutableListOf<NycPark>()

    parksList.forEach { park ->
        if (!park.signname.isNullOrEmpty() && !park.location.isNullOrEmpty() && !park.url.isNullOrEmpty()) {
            val eachPark = NycPark(
                signname = park.signname,
                location = park.location,
                waterfront = park.waterfront,
                url = park.url
            )

            filteredParks.add(eachPark)
        }
    }

    return filteredParks
}

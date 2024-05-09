package com.example.knownnyc.data.mappers

import com.example.knownnyc.data.local.provider.AssetsProvider
import com.example.knownnyc.data.models.NycParkResponse
import com.example.knownnyc.domain.models.Borough
import com.example.knownnyc.domain.models.NycPark
import org.json.JSONArray
import org.json.JSONObject

//TODO: Project 2

suspend fun ParksMapper(parkslist: List<NycParkResponse>): List<NycPark> {

    return parkslist.filter {

        !it.signname.isNullOrEmpty() &&
                it.location != null && it.location.isNotEmpty() &&
                it.url != null && it.url.isNotEmpty()
    }.map { validResponse ->

        NycPark(
            signname = validResponse.signname!!,
            location = validResponse.location!!,
            waterfront = validResponse.waterfront,
            url = validResponse.url!!
        )
    }

}
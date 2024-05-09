package com.example.knownnyc.data.mappers

import com.example.knownnyc.data.local.provider.AssetsProvider
import com.example.knownnyc.domain.models.Borough
import com.example.knownnyc.domain.models.NycPark
import org.json.JSONArray
import org.json.JSONObject

//TODO: Project 2

suspend fun ParksMapper(
    jsonObj: JSONObject,
    localAssetsProvider: AssetsProvider,
): List<NycPark> {

    val jsonArray: JSONArray = jsonObj.getJSONArray("boroughs")

    val boroughs:MutableList<Borough> = mutableListOf<Borough>()

    for (i: Int in 0 until jsonArray.length()) {
        val obj: JSONObject = jsonArray.getJSONObject(i)
        val borough = Borough(
            boroCode = obj.getString("borough").first(),
            name = obj.getString("shortName"),
            longName = obj.getString("fullName"),
            image = localAssetsProvider.getDrawableResourceId(obj.getString("imageFilename")),
            null
        )
        boroughs.add(borough)
    }

    return boroughs
}
package com.example.knownyc.data.mappers

import com.example.knownnyc.data.local.provider.AssetsProvider
import com.example.knownnyc.domain.models.Borough
import org.json.JSONArray
import org.json.JSONObject

// map JSON to Borough data class

suspend fun boroughsMapper(
    jsonObj: JSONObject,
    localAssetsProvider: AssetsProvider,
): List<Borough> {

    val jsonArray:JSONArray = jsonObj.getJSONArray("boroughs")

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
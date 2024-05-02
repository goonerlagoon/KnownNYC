package com.example.knownnyc.data.mappers

import com.example.knownnyc.data.local.provider.AssetsProvider
import com.example.knownnyc.domain.models.Borough
import org.json.JSONObject

suspend fun boroughsMapper(
    jsonObj: JSONObject,
    localAssetsProvider: com.example.knownnyc.data.local.provider.AssetsProvider,
) : List<Borough> {

    val jsonArray = jsonObj.getJSONArray("boroughs")

    val boroughs = mutableListOf<Borough>()

    for (i in 0 until jsonArray.length()) {
        val obj = jsonArray.getJSONObject(i)
        val borough = Borough(
            boroCode = obj.getString("borough").first(),
            name = obj.getString("shortName"),
            longName = obj.getString("fullName"),
            image = localAssetsProvider.getDrawableResourceId(obj.getString("imageFilename"))
        )
    }

    return boroughs

}
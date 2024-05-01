package com.example.knownnyc.data.local.provider

interface AssetProvider {

    suspend fun getJsonData(filename: String) : String
    suspend fun getDrawableResourceID(name: String) : String

}

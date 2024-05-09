package com.example.knownnyc.data.remote.repositories

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.Either
import com.example.knownnyc.commons.TAG
import com.example.knownnyc.data.local.provider.AssetsProvider
import com.example.knownnyc.data.mappers.ParksMapper
import com.example.knownnyc.data.mappers.toError
import com.example.knownnyc.domain.models.NycPark
import com.example.knownnyc.util.AppConstants
import javax.inject.Inject

class NYCParksRepositoryImpl @Inject constructor(private val localAssetProvider: AssetsProvider,
) : NYCParksRepository {


    private suspend fun loadJsonAndMapData() : List<NycPark> {
        val json = loadJson()
        return ParksMapper(json, localAssetProvider)
    }


    private suspend fun loadJson() : JSONObject {
        val jsonString = localAssetProvider.getJsonData(AppConstants.BOROUGHS_JSON)
        return JSONObject(jsonString)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getParks(): Either<AppError, List<NycPark>> {
        Log.d(TAG, "assets provider loading boroughs from JSON file")
        return try {
            Either.Data(
                loadJsonAndMapData()
            )
        } catch (e: Exception) {
            Either.Error(
                e.toError()
            )
        }
    }
}
package com.example.knownnyc.domain.repositories

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.Either
import com.example.knownnyc.commons.TAG
import com.example.knownnyc.data.local.provider.AssetsProvider
import com.example.knownnyc.data.mappers.toError
import com.example.knownnyc.domain.models.Borough
import com.example.knownnyc.util.AppConstants
import com.example.knownyc.data.mappers.boroughsMapper
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

class BoroughsRepositoryImpl @Inject constructor(private val localAssetProvider: AssetsProvider,
    ) : BoroughsRepository {


    private suspend fun loadJsonAndMapData() : List<Borough> {
          val json = loadJson()
          return boroughsMapper(json, localAssetProvider)
    }


    private suspend fun loadJson() : JSONObject {
        val jsonString = localAssetProvider.getJsonData(AppConstants.BOROUGHS_JSON)
        return JSONObject(jsonString)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getBoroughs(): Either<AppError, List<Borough>> {
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
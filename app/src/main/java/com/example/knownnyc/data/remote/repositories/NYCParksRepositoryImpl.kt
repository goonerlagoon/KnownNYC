package com.example.knownnyc.data.remote.repositories

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.Either
import com.example.knownnyc.commons.TAG
import com.example.knownnyc.data.mappers.parksMapper
import com.example.knownnyc.domain.models.NycPark
import javax.inject.Inject

class NYCParksRepositoryImpl @Inject constructor(
    private val parkApi: NycOpenDataApiService
) : NycParksRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getParks(borough: String): Either<AppError, List<NycPark>> {
         return try {

            val rawData = parkApi.getNycParks(borough)
            val mappedParks = parksMapper(rawData)


//            Log.d(TAG, "this is response: $response")
//

            Either.Data(mappedParks)
        } catch (e: Exception) {
            Log.e(TAG, "err msg: $e")
            Either.Error(
                AppError("Err - $e", e)
            )
        }
    }
}


package com.example.knownnyc.data.remote.repositories

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.Either
import com.example.knownnyc.data.mappers.parksMapper
import com.example.knownnyc.data.mappers.toError
import com.example.knownnyc.domain.models.NycPark

class NYCParksRepositoryImpl(
    private val parkApi: NycOpenDataApiService
) : NycParksRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getParks(boroCode: Char): Either<AppError, List<NycPark>> {
        return try {

            val response = parkApi.getNycParks(boroCode.toString(), false)

            val parks = parksMapper(response)

            Either.Data(parks)
        } catch (e: Exception) {
            Either.Error(
                AppError("Can't get parks for $boroCode", e)
            )

        }
    }
}
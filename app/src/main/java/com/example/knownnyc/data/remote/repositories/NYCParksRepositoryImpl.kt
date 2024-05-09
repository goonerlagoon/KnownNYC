package com.example.knownnyc.data.remote.repositories

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.Either
import com.example.knownnyc.data.mappers.ParksMapper
import com.example.knownnyc.data.mappers.toError
import com.example.knownnyc.domain.models.Borough
import com.example.knownnyc.domain.models.NycPark

import retrofit2.HttpException

class NYCParksRepositoryImpl(
    private val apiService: NycOpenDataApiService
) : NycParksRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getParks(boroCode: Char): Either<AppError, List<NycPark>> {
        return try {

            val response = apiService.getNycParks(boroCode.toString(), false)

            val parks = ParksMapper(response)

            Either.Data(parks)
        } catch (e: Exception) { 
            Either.Error(
                e.toError()
            )

        }
    }
}
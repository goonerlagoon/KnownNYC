package com.example.knownnyc.data.remote.repositories

import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.Either
import com.example.knownnyc.domain.models.NycPark

interface NycParksRepository {
    suspend fun getParks(borough: String): Either<AppError, List<NycPark>>
}


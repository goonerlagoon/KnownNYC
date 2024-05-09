package com.example.knownnyc.data.remote.repositories

import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.Either
import com.example.knownnyc.data.models.NycParkResponse
import com.example.knownnyc.domain.models.Borough
import com.example.knownnyc.domain.models.NycPark

interface NycParksRepository {
    suspend fun getParks(boroCode: Char): Either<AppError, List<NycPark>>
}


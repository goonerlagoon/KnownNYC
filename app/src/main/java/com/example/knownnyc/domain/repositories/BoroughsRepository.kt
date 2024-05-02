package com.example.knownnyc.domain.repositories

import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.Either
import com.example.knownnyc.domain.models.Borough

interface BoroughsRepository {
    suspend fun getBoroughs() : Either<AppError, List<Borough>>
}
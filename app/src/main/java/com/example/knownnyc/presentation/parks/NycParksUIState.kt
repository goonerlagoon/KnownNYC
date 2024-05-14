package com.example.knownnyc.presentation.parks

import com.example.knownnyc.commons.AppError
import com.example.knownnyc.domain.models.NycPark

data class NycParksUIState(

    val isLoading: Boolean = false,
    val parks: List<NycPark> = emptyList(),
    val error: AppError? = null,

)

//TODO: Project 2
package com.example.knownnyc.presentation.boroughs

import com.example.knownnyc.commons.AppError
import com.example.knownnyc.domain.models.Borough

data class BoroughsUIState(
    val isLoading: Boolean = false,
    val boroughs: List<Borough> = emptyList(),
    val error: AppError? = null,
) {

}

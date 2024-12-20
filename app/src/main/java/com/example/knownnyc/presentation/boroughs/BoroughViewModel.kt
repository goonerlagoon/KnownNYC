package com.example.knownnyc.presentation.boroughs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knownnyc.commons.Either
import com.example.knownnyc.commons.Event
import com.example.knownnyc.commons.TAG
import com.example.knownnyc.commons.sendEvent
import com.example.knownnyc.domain.repositories.BoroughsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoroughViewModel @Inject constructor(
    private val repository: BoroughsRepository
) : ViewModel () {
    private val _state = MutableStateFlow(BoroughsUIState())
    val state = _state.asStateFlow()

    init {
        Log.d(TAG, "ViewModel init getting boroughs")
        getBorough()
    }

    private fun getBorough() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            val result = repository.getBoroughs()
            when (result) {
                is Either.Data -> {
                    Log.d(TAG, "loaded data successfully")
                    _state.update {
                        it.copy(
                            boroughs = result.value
                        )
                    }
                }
                is Either.Error -> {
                    Log.e(TAG, "Error loading boroughs")
                    _state.update {
                        it.copy(
                            error = result.error
                        )
                    }
                sendEvent(Event.Toast(result.error.message))
                }
            }

            _state.update {
                it.copy(isLoading = false)
            }
            //load the data from a repo
        }
    }
}
package com.example.knownnyc.presentation.parks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knownnyc.commons.Either
import com.example.knownnyc.commons.Event
import com.example.knownnyc.commons.TAG
import com.example.knownnyc.commons.sendEvent
import com.example.knownnyc.data.remote.repositories.NycParksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NycParksViewModel @Inject constructor(
    private val repository: NycParksRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NycParksUIState())
    val state = _state.asStateFlow()

//    init {
//        Log.d(TAG, "ViewModel init getting parks")
//        getPark()
//    }

     fun loadParksForBorough(boroCode: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = repository.getParks(boroCode)

            when (result) {
                is Either.Data -> {
                    _state.update {
                        it.copy(
                            parks = result.value,
                            isLoading = false
                        )
                    }
                }

                is Either.Error -> {
                    _state.update {
                        it.copy(
                            error = result.error,
                            isLoading = false
                        )
                    }

                    sendEvent(Event.Toast(result.error.message))
                }
            }
        }
    }
}

//TODO: Project 2
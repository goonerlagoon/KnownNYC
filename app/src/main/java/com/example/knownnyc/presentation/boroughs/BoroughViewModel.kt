package com.example.knownnyc.presentation.boroughs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoroughViewModel @Inject constructor(

) : ViewModel () {
    private val _state = MutableStateFlow(BoroughsUIState())
    val state = _state.asStateFlow()

    init {
        getBorough()
    }

    private fun getBorough() {
        viewModelScope.launch {
            //load the data from a repo
        }
    }
}
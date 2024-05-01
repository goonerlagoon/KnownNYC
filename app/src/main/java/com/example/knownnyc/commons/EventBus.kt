package com.example.knownnyc.commons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

object EventBus {
    private val _events = Channel<Any>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: Any) {
        _events.send(event)
    }
}

sealed interface Event {
    data class Toast(val message: String) : Event
    data object NavigatetoHomeScreen : Event
}

fun ViewModel.sendEvent(event: Event) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}
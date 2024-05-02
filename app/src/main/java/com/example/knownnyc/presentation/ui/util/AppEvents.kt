package com.example.knownnyc.presentation.ui.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.knownnyc.commons.Event
import com.example.knownnyc.commons.EventBus

@Composable
fun AppEvents(context: Context) {
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = lifecycleOwner) {
        EventBus.events.collect { event ->
            when (event) {
                is Event.Toast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
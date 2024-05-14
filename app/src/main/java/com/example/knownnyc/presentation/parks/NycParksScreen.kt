package com.example.knownnyc.presentation.parks

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.knownnyc.presentation.ui.util.LoadingDialog


@Composable
fun NycParksScreen(boroCode: String) {

    val viewModel : NycParksViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val (currentUrl, setCurrentUrl) = remember { mutableStateOf("") }

    LaunchedEffect(key1 = boroCode ) {
        viewModel.loadParksForBorough(boroCode)
    }

    LoadingDialog(isLoading = state.isLoading)

    LazyColumn {
        items(state.parks) { park ->
            NycParkCard(park = park, onClick = {
//              openUrl(context, park.url)
                setCurrentUrl(park.url)
                setShowDialog(true)
            })
        }
      }

    if (showDialog) {
        WebViewDialog(url = currentUrl) {
            setShowDialog(false) // This will close the dialog when dismissed
        }
    }
}

@Composable
fun WebViewDialog(url: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        WebViewScreen(url = url, modifier = Modifier.fillMaxSize())
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }
    )
}

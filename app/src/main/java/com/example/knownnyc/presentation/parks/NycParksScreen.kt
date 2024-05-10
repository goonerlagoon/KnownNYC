package com.example.knownnyc.presentation.parks

import NycParkCard
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.knownnyc.commons.TAG
import com.example.knownnyc.domain.models.NycPark
import com.example.knownnyc.presentation.ui.util.LoadingDialog

@Composable
fun NycParksScreen(boroCode: String,
                   modifier: Modifier = Modifier,
                   onParkClicked: (String) -> Unit = {},
) {

    val viewModel : NycParksViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LoadingDialog(isLoading = state.isLoading)

    LaunchedEffect(key1 = boroCode) {
        viewModel.loadParksForBorough(boroCode)
    }



    LazyColumn (
        modifier = modifier
    ) {
        items(state.parks) { park ->
            NycParkCard(park) {
                //TODO: navigate to selected Borough
                Log.d(TAG, "clicked: ${park.signname}")
//                onParkClicked(park.url)
            }
        }
    }
}

@Preview
@Composable
fun NycParkScreenPreview() {

    val myPark = NycPark(
        signname = "Hunts gqergeergeqrg",
        location = "lafayette ergeqrgerg",
        waterfront = false,
        url = ""
    )
    NycParkCard(myPark)
}

//TODO: Project 2
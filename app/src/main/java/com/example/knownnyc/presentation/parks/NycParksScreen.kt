package com.example.knownnyc.presentation.parks

import NycParkCard
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.knownnyc.commons.TAG
import com.example.knownnyc.domain.models.NycPark
import com.example.knownnyc.presentation.boroughs.BoroughCard
import com.example.knownnyc.presentation.boroughs.BoroughViewModel
import com.example.knownnyc.presentation.ui.util.LoadingDialog

@Composable
fun NycParksScreen(boroCode: Char,
                   modifier: Modifier = Modifier,
                   onParkClicked: (String) -> Unit = {},
) {

    val viewModel : NycParksViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()


    viewModel.loadParksForBorough(boroCode)

    LoadingDialog(isLoading = state.isLoading)
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

//TODO: Project 2
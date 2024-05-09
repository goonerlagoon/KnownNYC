package com.example.knownnyc.presentation.parks

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.knownnyc.commons.TAG
import com.example.knownnyc.domain.models.NycPark
import com.example.knownnyc.presentation.boroughs.BoroughCard
import com.example.knownnyc.presentation.boroughs.BoroughViewModel
import com.example.knownnyc.presentation.ui.util.LoadingDialog

@Composable
fun NycParksScreen(
    modifier: Modifier = Modifier,
    onParkClicked: (Char, String) -> Unit
) {

    val viewModel : BoroughViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingDialog(isLoading = state.isLoading)
    LazyColumn (
        modifier = modifier
    ) {
        items(state.boroughs) { borough ->
            BoroughCard(
                name = borough.name,
                painter = painterResource(id = borough.image),
                contentDescription = borough.longName,
            ) {
                //TODO: navigate to selected Borough
                Log.d(TAG, "clicked: ${borough.name}")
                onBoroughClicked(borough.boroCode, borough.name)
            }
        }


    }
}

//TODO: Project 2
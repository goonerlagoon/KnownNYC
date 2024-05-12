package com.example.knownnyc.presentation.boroughs

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
import com.example.knownnyc.presentation.ui.util.LoadingDialog

@Composable
fun BoroughsScreen(
    modifier: Modifier = Modifier,
    onBoroughClicked: (Char, String) -> Unit
) {

  val viewModel : BoroughViewModel = hiltViewModel()
  val state by viewModel.state.collectAsStateWithLifecycle()

  val context = LocalContext.current


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
package com.example.knownnyc.presentation.boroughs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BoroughScreen(
    modifier: Modifier = Modifier,
) {

  val viewModel : BoroughViewModel = hiltViewModel()
  val state by viewModel.state.collectAsStateWithLifecycle()
    // we need:
    //
    //
  LazyColumn (
        modifier = modifier
  ) {
    items(state.boroughs) { borough ->
        BoroughCard(
            name = borough.name,
            painter = painterResource(id = borough.image),
            contentDescription = borough.longName
        )
    }


    }
}
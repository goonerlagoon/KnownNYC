package com.example.knownnyc.presentation.boroughs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.knownnyc.R

@Composable
fun BoroughCard(
    name: String,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},

) {

    val height = 148.dp
    Card(
        modifier = modifier
            .padding(6.dp)
            .shadow(
                elevation = 6.dp,
                spotColor = MaterialTheme.colorScheme.surfaceTint
            )
            .requiredHeight(height)
            .clickable {
                onClick()
            }

    ) {

      Box(
        modifier = modifier
            .fillMaxSize()
      ) {

        Text(
            text = name,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
      }
    }

}

@Preview
@Composable
fun BoroughCardPreview () {
    BoroughCard(name = "NYC Parks", painter = painterResource(id = R.drawable.bronx), contentDescription = "BX")
}
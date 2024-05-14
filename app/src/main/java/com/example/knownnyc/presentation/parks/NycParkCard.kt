package com.example.knownnyc.presentation.parks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knownnyc.R
import com.example.knownnyc.domain.models.NycPark


@Composable
fun NycParkCard(
    park: NycPark,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    val logo = painterResource(id = R.drawable.nyc_parks_logo)
    val water = painterResource(id = R.drawable.waves_24px)
    val height = 150.dp

    val mintGreen = Color(0xFF98FF98)
    val leafGreen = Color(0xFF4CAF50)

    Card(
        modifier = modifier
            .padding(6.dp)
            .shadow(
                elevation = 6.dp,
                spotColor = MaterialTheme.colorScheme.surfaceTint
            )
            .background(color = mintGreen)
            .border(width = 6.dp, color = leafGreen)
            .requiredHeight(height)
            .clickable(onClick = onClick),

    ) {
        Row(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
            Icon(
                painter = logo,
                contentDescription = "Park Icon",
                modifier = Modifier
                    .size(60.dp)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column(
                modifier = Modifier
                    .weight(5f)
                    .padding(start = 16.dp),
            ) {
                Text(
                    text = park.signname,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Text(
                    text = park.location,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (park.waterfront) {

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = water,
                    contentDescription = "water park",
                    modifier = Modifier.align(Alignment.Top)
                        .background(color = Color.Blue)
                        .size(24.dp)
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun NycParkCardPreview() {
//
//    val myPark = NycPark(
//        signname = "Hunts gqergeergeqrg testting",
//        location = "lafayette ergeqrgerg test",
//        waterfront = true,
//        url = ""
//    )
//    NycParkCard(myPark)
//}
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knownnyc.R


@Composable
fun NycParkCard(
    name: String,
    address: String,
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
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.nyc_parks_logo), 
                contentDescription = "Park Icon",
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .weight(5f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = address,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview
@Composable
fun NycParkCardPreview() {
    NycParkCard(
        name = "Hunts Point",
        address = "Lafayette Av"
    )
}

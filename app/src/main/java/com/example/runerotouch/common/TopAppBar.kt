package com.example.runerotouch.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.runerotouch.R
import com.example.runerotouch.ui.theme.Brown900
import com.example.runerotouch.ui.theme.Brown600
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random


@Preview
@Composable
private fun TopAppBarPreview() {
    TopAppBar(
        onClick = {}
    )
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    var currentTime by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            val currentDate = sdf.format(Date())
            currentTime = currentDate
            delay(1000) // Обновляем каждую секунду
        }
    }

    var temperature by remember { mutableStateOf(85) }

    LaunchedEffect(Unit) {
        while (true) {
            temperature = Random.nextInt(85, 95)
            delay(1000) // Обновляем каждую секунду
        }
    }
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .clickable(
                        onClick = onClick,
                    ),
            ) {
                Icon(
                    modifier = Modifier
                        .padding(
                            start = 26.dp,
                            end = 12.dp,
                        ),
                    painter = painterResource(id = R.drawable.ic_union),
                    contentDescription = null,
                    tint = Brown900
                )
                Text(
                    text = "RUNERO Touch",
                    color = Brown900,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 26.dp,
                            top = 15.dp,
                            end = 26.dp,
                        ),
                    text = currentTime,
                    color = Brown600,
                    style = MaterialTheme.typography.titleMedium,
                )
                VerticalDivider(
                    modifier = Modifier
                        .height(55.dp),
                    thickness = 0.2.dp,
                    color = Brown900
                )
                Row(
                    modifier = Modifier
                        .padding(
                            start = 26.dp,
                            top = 15.dp,
                            end = 26.dp,
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$temperature°",
                        color = Brown600,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_primary),
                        contentDescription = null,
                        tint = Brown600,
                    )
                }
                VerticalDivider(
                    modifier = Modifier
                        .height(55.dp),
                    thickness = 0.2.dp,
                    color = Brown900
                )
                Row(
                    modifier = Modifier
                        .padding(
                            start = 26.dp,
                            top = 15.dp,
                            end = 50.dp,
                        ),
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(11.dp),
                        painter = painterResource(id = R.drawable.ic_russia),
                        contentDescription = null,
                    )
                    Text(
                        text = "RU",
                        color = Brown600,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 0.3.dp,
            color = Brown900
        )
    }
}
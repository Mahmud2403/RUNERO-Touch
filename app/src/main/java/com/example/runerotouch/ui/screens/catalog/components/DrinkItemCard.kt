package com.example.runerotouch.ui.screens.catalog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.runerotouch.R
import com.example.runerotouch.domain.model.Drink
import com.example.runerotouch.domain.model.drinkList
import com.example.runerotouch.ui.theme.Black700
import com.example.runerotouch.ui.theme.Black800
import com.example.runerotouch.ui.theme.Brown500
import com.example.runerotouch.ui.theme.Brown800
import com.example.runerotouch.ui.theme.Orange500


@Preview
@Composable
private fun DrinkItemCardPreview() {
    DrinkItemCard(
        drink = Drink.mock
    )
}

@Composable
fun DrinkItemCard(
    modifier: Modifier = Modifier,
    drink: Drink,
) {
    Column(
        modifier = modifier
            .background(Black800)
    ) {
        Image(
            modifier = Modifier
                .padding(28.dp)
                .size(166.dp),
            painter = painterResource(
                id = if(drink.imageIndex == 0) R.drawable.light_drink
                else R.drawable.dark_drink
            ),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 17.dp),
            text = drink.name,
            color = Brown500,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(26.dp))
        Row(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            Black800,
                            Black700,
                        )
                    )
                )
                .fillMaxWidth()
                .padding(
                    vertical = 9.dp,
                    horizontal = 16.dp
                ),
            horizontalArrangement = if (drink.price != 0) Arrangement.SpaceBetween else Arrangement.Center,
        ) {
            Text(
                text = "${drink.size}",
                color = Brown800,
                style = MaterialTheme.typography.titleMedium,
            )
            if (drink.price != 0)
            Text(
                text = "${drink.price} ₽",
                color = Orange500,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
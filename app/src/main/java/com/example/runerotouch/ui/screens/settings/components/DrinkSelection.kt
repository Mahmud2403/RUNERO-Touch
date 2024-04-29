package com.example.runerotouch.ui.screens.settings.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.runerotouch.R
import com.example.runerotouch.ui.theme.Orange500

@Composable
fun DrinkSelection(
    onDrinkSelected: (Int) -> Unit,
    selectedDrinkIndex: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Объявляем напитки
        val drinks = listOf(
            R.drawable.light_drink to "Светлый напиток",
            R.drawable.dark_drink to "Темный напиток"
        )

        // Отображаем напитки
        drinks.forEachIndexed { index, (drawableId, name) ->
            Box(
                modifier = Modifier
                    .clickable { onDrinkSelected(index) }
                    .padding(8.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    modifier = Modifier
                        .size(190.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    painter = painterResource(id = drawableId),
                    contentDescription = name,
                )
                if (selectedDrinkIndex == index) {
                    Icon(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Orange500)
                        ,
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}
package com.example.runerotouch.ui.screens.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.runerotouch.common.TopAppBar
import com.example.runerotouch.domain.model.drinkList
import com.example.runerotouch.ui.screens.catalog.components.DrinkItemCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    onClickSettings: () -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopAppBar(
                onClick = onClickSettings
            )
        },
        containerColor = Color(0xFF000000)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 23.dp,
                    top = it.calculateTopPadding() + 24.dp,
                    end = 23.dp,
                ),
            columns = GridCells.Adaptive(160.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            items(
                items = drinkList
            ) { drink ->
                DrinkItemCard(
                    drink = drink,
                )
            }
        }
    }
}
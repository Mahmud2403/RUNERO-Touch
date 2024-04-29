package com.example.runerotouch.ui.screens.settings

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.runerotouch.common.TopAppBar
import com.example.runerotouch.ui.screens.settings.components.DrinkSelection
import com.example.runerotouch.ui.screens.settings.components.FreePriceToggle
import com.example.runerotouch.ui.screens.settings.components.NameTextField
import com.example.runerotouch.ui.screens.settings.components.PriceTextField
import com.example.runerotouch.ui.theme.Brown50
import com.example.runerotouch.ui.theme.Orange500
import com.example.runerotouch.util.ComposableLifecycle

@Preview()
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(
        onClickCatalog = {}
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    onClickCatalog: () -> Unit,
) {
    val uiState by viewModel.settingsUiState.collectAsState()

    val scrollState = rememberScrollState()

    ComposableLifecycle(
        onEvent = { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    viewModel.saveData()
                }

                else -> Unit
            }
        }
    )
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopAppBar(
                onClick = onClickCatalog
            )
        },
        containerColor = Color.Black
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding()
                )
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Column {
                    NameTextField(
                        name = uiState.name,
                        onChangeName = viewModel::changeName
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    PriceTextField(
                        price = uiState.price,
                        onChangePrice = viewModel::changePrice,
                        enabled = !uiState.freeDrink
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    FreePriceToggle(
                        checked = uiState.freeDrink,
                        onCheckedChange = viewModel::priceToggle
                    )
                }
                DrinkSelection(
                    onDrinkSelected = viewModel::selectDrink,
                    selectedDrinkIndex = uiState.drinkIndex,
                )
            }
            Spacer(modifier = Modifier.height(62.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Button(
                    modifier = Modifier
                        .padding(start = 72.dp),
                    onClick = {
                        viewModel.addDrinkToList()
                        onClickCatalog()

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange500,
                        contentColor = Brown50,
                    )

                ) {
                    Text(
                        text = "Сохранить"
                    )
                }
            }
        }
    }
}
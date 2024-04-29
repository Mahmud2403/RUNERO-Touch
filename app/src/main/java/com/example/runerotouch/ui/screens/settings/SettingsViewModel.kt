package com.example.runerotouch.ui.screens.settings

import android.content.Context
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.runerotouch.domain.model.Drink
import com.example.runerotouch.domain.model.drinkList
import com.example.runerotouch.util.removeLeadingZeros
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var _settingsUiState = MutableStateFlow(SettingsUiState())
    val settingsUiState = _settingsUiState.asStateFlow()

    private val sharedPreferences =
        context.getSharedPreferences("SettingsViewModel", Context.MODE_PRIVATE)

    private val savedName = sharedPreferences.getString("name", "") ?: ""
    private val savedPrice = sharedPreferences.getString("price", "") ?: ""
    private val savedFreeDrink = sharedPreferences.getBoolean("freeDrink", false)
    private val savedDrinkIndex = sharedPreferences.getInt("drinkIndex", 0)

    init {
        // Загрузка сохраненных данных при создании ViewModel
        _settingsUiState.update {
            SettingsUiState(savedName, savedPrice, savedFreeDrink, savedDrinkIndex)
        }
    }


    fun selectDrink(index: Int) {
        _settingsUiState.update { currentState ->
            currentState.copy(
                drinkIndex = index
            )
        }
    }

    fun changeName(newValue: String) {
            _settingsUiState.update { currentState ->
                currentState.copy(
                    name = newValue,
                )

        }
    }

    fun changePrice(newPrice: String) {
        if (newPrice.isDigitsOnly()) {
            _settingsUiState.update { currentState ->
                currentState.copy(
                    price = removeLeadingZeros(newPrice)
                )
            }
        }
    }

    fun priceToggle(boolean: Boolean) {
        _settingsUiState.update { currentState ->
            currentState.copy(
                freeDrink = boolean,
                price = "0"
            )
        }
    }

    fun addDrinkToList() {
        val currentUiState = _settingsUiState.value
        val drinkIndex = currentUiState.drinkIndex
        val name = currentUiState.name
        val price = currentUiState.price.toIntOrNull() ?: 0 // Преобразуем цену в Int

        if (drinkIndex != -1 && name.isNotBlank()) {
            val newDrink = Drink(
                imageIndex = drinkIndex,
                name = name,
                size = 0.33,
                price = price
            )
            drinkList.add(newDrink)
        }
    }

    fun saveData() {
        val currentState = _settingsUiState.value
        sharedPreferences.edit().apply {
            putString("name", currentState.name)
            putString("price", currentState.price)
            putBoolean("freeDrink", currentState.freeDrink)
            putInt("drinkIndex", currentState.drinkIndex)
            apply()
        }
    }
}

data class SettingsUiState(
    val name: String = "",
    val price: String = "",
    val freeDrink: Boolean = false,
    val drinkIndex: Int = 0,
)
package com.example.runerotouch.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CurrencyRuble
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.runerotouch.ui.theme.Black600
import com.example.runerotouch.ui.theme.Black700
import com.example.runerotouch.ui.theme.Brown100
import com.example.runerotouch.ui.theme.Brown500
import com.example.runerotouch.util.numberMask
import com.example.runerotouch.util.transformationOf


@Composable
fun PriceTextField(
    price: String,
    onChangePrice: (String) -> Unit,
    enabled: Boolean,
) {
    val focusManager = LocalFocusManager.current

    Column {
        Text(
            text = "Цена",
            color = Brown500,
            style = MaterialTheme.typography.labelMedium,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.4f),
            value = price,
            onValueChange = {
                onChangePrice(it)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Black600,
                focusedContainerColor = Black600,
                focusedTextColor = Brown100,
                unfocusedTextColor = Brown100,
                focusedIndicatorColor = Black600,
                unfocusedIndicatorColor = Black600,
                cursorColor = Brown100,
                disabledContainerColor = Black700,
                disabledIndicatorColor = Black700,
                disabledTextColor = Brown100,
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.CurrencyRuble,
                    contentDescription = null,
                    tint = Brown500,
                )
            },
            visualTransformation = transformationOf(
                mask = numberMask(price.length)
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    focusManager.clearFocus()
                }
            ),
            enabled = enabled,
            maxLines = 1,
        )
    }
}
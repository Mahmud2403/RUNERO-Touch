package com.example.runerotouch.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.runerotouch.ui.theme.Black600
import com.example.runerotouch.ui.theme.Brown100
import com.example.runerotouch.ui.theme.Brown500

@Composable
fun NameTextField(
    name: String,
    onChangeName: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Наименование",
            color = Brown500,
            style = MaterialTheme.typography.labelMedium,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.4f),
            value = name,
            onValueChange = {
                onChangeName(it)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Black600,
                focusedContainerColor = Black600,
                focusedTextColor = Brown100,
                unfocusedTextColor = Brown100,
                focusedIndicatorColor = Black600,
                unfocusedIndicatorColor = Black600,
                cursorColor = Brown100,
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    focusManager.clearFocus()
                }
            ),
            maxLines = 1,
        )
    }
}
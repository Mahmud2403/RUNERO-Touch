package com.example.runerotouch.ui.screens.settings.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.runerotouch.ui.theme.Black600
import com.example.runerotouch.ui.theme.Brown600
import com.example.runerotouch.ui.theme.Orange500

@Composable
fun FreePriceToggle(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .border(
                width = 1.dp,
                color = Color(0xFF261A18),
                shape = RoundedCornerShape(size = 12.dp)
            ),
        value = "Продавать бесплатно",
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            Switch(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Orange500,
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Black600,
                )
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Black,
            focusedContainerColor = Color.Black,
            focusedTextColor = Brown600,
            unfocusedTextColor = Brown600,
            focusedIndicatorColor = Black600,
            unfocusedIndicatorColor = Black600,
        ),
    )
}
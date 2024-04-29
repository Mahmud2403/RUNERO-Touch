package com.example.runerotouch.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.math.absoluteValue

fun removeLeadingZeros(input: String): String {
    val firstNonZeroIndex = input.indexOfFirst { it != '0' }

    return if (firstNonZeroIndex == -1 && input.isNotEmpty()) {
        ""
    } else if (firstNonZeroIndex > 0) {
        input.substring(firstNonZeroIndex)
    } else {
        input
    }
}

fun numberMask(size: Int) =
    CharArray(size) { '#' }
        .concatToString()
        .chunked(3)
        .joinToString(" ")
        .reversed()


fun transformationOf(
    mask: String,
    endChar: String = "",
    firstChar: String = "",
) =
    object: VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            var out = ""
            var maskIndex = 0
            text.forEach { char ->
                while(mask.indices.filter { mask[it] != '#' }
                        .contains(maskIndex)) {
                    out += mask[maskIndex]
                    maskIndex++
                }; out += char; maskIndex++
            }
            return TransformedText(
                AnnotatedString(firstChar + out + endChar),
                object: OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        val offsetValue = offset.absoluteValue
                        if(offsetValue == 0) return 0
                        var numberOfHashtags = 0
                        val masked = mask
                            .takeWhile {
                                if(it == '#') numberOfHashtags++
                                numberOfHashtags < offsetValue
                            }
                        return (masked.length + 1)
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        return mask
                            .take(offset.absoluteValue)
                            .count { it == '#' }
                    }
                }
            )
        }
    }

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

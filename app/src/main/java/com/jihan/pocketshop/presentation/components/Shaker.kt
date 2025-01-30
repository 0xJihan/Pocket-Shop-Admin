package com.jihan.pocketshop.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun Shaker() {
    val shake = remember { Animatable(0f) }
    var trigger by remember { mutableStateOf(0L) }
    LaunchedEffect(trigger) {
        if (trigger != 0L) {
            for (i in 0..10) {
                when (i % 2) {
                    0 -> shake.animateTo(5f, spring(stiffness = 100_000f))
                    else -> shake.animateTo(-5f, spring(stiffness = 100_000f))
                }
            }
            shake.animateTo(0f)
        }
    }

    Box(
        modifier = Modifier
            .clickable { trigger = System.currentTimeMillis() }
            .offset { IntOffset(shake.value.roundToInt(), y = 0) }
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Text(text = "Shake me")
    }
}
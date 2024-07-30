package com.example.mtglifecounter.Components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.painterResource
import com.example.mtglifecounter.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive


@Composable
fun Buttons(
    onShortClick: () -> Unit,
    onLongPressAction: () -> Unit,
    iconID: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val viewConfiguration = LocalViewConfiguration.current

    val interactionSource = remember { MutableInteractionSource() }
    var isLongClick by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collectLatest { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    isLongClick = false
                    delay(viewConfiguration.longPressTimeoutMillis)
                    if (isActive) {
                        isLongClick = true
                        // Comienza la acción de incremento o decremento mientras el botón está presionado
                        while (isActive && isLongClick) {
                            onLongPressAction()
                            delay(100) // Intervalo de actualización (100 ms o ajusta según sea necesario)
                        }
                        Toast.makeText(context, "Long click", Toast.LENGTH_SHORT).show()
                    }
                }

                is PressInteraction.Release -> {
                    if (isLongClick.not()) {
                        onShortClick()
                    }
                    // Cuando se libera el botón, deja de ejecutar la acción de largo
                    isLongClick = false
                }
            }
        }
    }

    IconButton(
        onClick = {
            // Este onClick es necesario para el comportamiento normal del clic.
        },
        modifier = modifier,
        interactionSource = interactionSource
    ) {
        Icon(
            painter = painterResource(id = iconID),
            contentDescription = contentDescription,
            tint = Color.White
        )
    }
}
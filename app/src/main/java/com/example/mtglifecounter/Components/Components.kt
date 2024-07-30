package com.example.mtglifecounter.Components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun Buttons(
    onShortClick: () -> Unit,
    onLongPressAction: () -> Unit,
    iconID: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {

    val interactionSource = remember { MutableInteractionSource() }

    HandleLongPress(
        interactionSource = interactionSource,
        onShortClick = onShortClick,
        onLongPressAction = onLongPressAction
    )


    IconButton(
        onClick = {
            // Este onClick es necesario para el comportamiento normal del clic
            // pero el manejo de la pulsación se realiza en HandleLongPress
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
@Composable
fun DisplayHealth(){

}
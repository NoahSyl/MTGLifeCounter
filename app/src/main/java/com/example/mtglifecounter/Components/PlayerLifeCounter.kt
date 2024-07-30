package com.example.mtglifecounter.Components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.Data.Player
import com.example.mtglifecounter.R
import com.example.mtglifecounter.ViewModel.GameViewModel
import com.example.mtglifecounter.ui.theme.customTextStyle

@Composable
fun PlayerLifeCounter(
    gameViewModel: GameViewModel,
    player: Player,
    backgroundID: Int,
    rotateAngle: Float = 0f,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf(player.name) }
    var life by rememberSaveable { mutableStateOf(player.life) }

    Box(
        modifier = modifier
            .rotate(rotateAngle)
            .fillMaxSize()
            .border(0.3.dp, Color.White)
    ) {
        Image(
            painter = painterResource(id = backgroundID),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = name,
                onValueChange = {
                    name = it
                    player.name = it
                },
                textStyle = customTextStyle.copy(
                    color = Color.White,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                modifier = Modifier.background(Color.Transparent).padding(4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Buttons(
                    onShortClick = {
                        gameViewModel.decrementLife(player)
                        life = player.life
                    },
                    onLongPressAction = {
                        gameViewModel.decrementLife(player)
                        life = player.life
                    },
                    iconID = R.drawable.botonminus,
                    contentDescription = "Decrement life",
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    life.toString(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(16.dp)
                        .bounceClick(),
                    style = customTextStyle.copy(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Buttons(
                    onShortClick = {
                        gameViewModel.incrementLife(player)
                        life = player.life
                    },
                    onLongPressAction = {
                        gameViewModel.incrementLife(player)
                        gameViewModel.incrementLife(player)
                        life = player.life
                    },
                    iconID = R.drawable.botonplus,
                    contentDescription = "Increment life",
                    modifier = Modifier
                )
            }
        }
    }
}
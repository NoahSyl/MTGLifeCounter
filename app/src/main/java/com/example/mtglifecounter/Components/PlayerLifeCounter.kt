package com.example.mtglifecounter.Components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.Data.Player
import com.example.mtglifecounter.R
import com.example.mtglifecounter.ViewModel.GameViewModel
import com.example.mtglifecounter.ui.theme.customTextStyle


enum class CounterType {
    LIFE, COMMANDER_DAMAGE, POISON, ENERGY
}

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
    var commanderDamage by rememberSaveable { mutableStateOf(player.commanderDamage) }
    var poisonCounter by rememberSaveable { mutableStateOf(player.poisonCounter) }
    var energyCounter by rememberSaveable { mutableStateOf(player.energyCounter) }

    var counterType by rememberSaveable { mutableStateOf(CounterType.LIFE) }


    fun getCounterValue(): Int {
        return when (counterType) {
            CounterType.LIFE -> life
            CounterType.COMMANDER_DAMAGE -> commanderDamage
            CounterType.POISON -> poisonCounter
            CounterType.ENERGY -> energyCounter
        }
    }

    fun getCounterIcon(): Int {
        return when (counterType) {
            CounterType.LIFE -> R.drawable.life__icon
            CounterType.COMMANDER_DAMAGE -> R.drawable.comander_icon
            CounterType.POISON -> R.drawable.toxic_icon
            CounterType.ENERGY -> R.drawable.energy_icon
        }
    }

    fun incrementCounter() {
        when (counterType) {
            CounterType.LIFE -> {
                gameViewModel.incrementLife(player)
                life = player.life
            }

            CounterType.COMMANDER_DAMAGE -> {
                commanderDamage++
                player.commanderDamage = commanderDamage
            }

            CounterType.POISON -> {
                poisonCounter++
                player.poisonCounter = poisonCounter
            }

            CounterType.ENERGY -> {
                energyCounter++
                player.energyCounter = energyCounter
            }
        }
    }


    fun decrementCounter() {
        when (counterType) {
            CounterType.LIFE -> {
                gameViewModel.decrementLife(player)
                life = player.life
            }

            CounterType.COMMANDER_DAMAGE -> {
                commanderDamage--
                player.commanderDamage = commanderDamage
            }

            CounterType.POISON -> {
                poisonCounter--
                player.poisonCounter = poisonCounter
            }

            CounterType.ENERGY -> {
                energyCounter--
                player.energyCounter = energyCounter
            }
        }
    }

    val orientation = LocalConfiguration.current.orientation

    Box(
        modifier = modifier
            .rotate(rotateAngle)
            .fillMaxSize()
            .border(0.2.dp, Color.White)
    ) {
        Image(
            painter = painterResource(id = backgroundID),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Icon(
                painter = painterResource(
                    id = getCounterIcon()
                ),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
            )
            

            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {

                val comanderOrLifeIcon =
                    if (counterType == CounterType.LIFE) {
                        R.drawable.comander_icon
                    } else {
                        R.drawable.life__icon
                    }

                Icon(
                    painter = painterResource(
                        id =
                        comanderOrLifeIcon
                    ),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                val commanderOrLifeText =
                    if (counterType == CounterType.LIFE) {
                        commanderDamage
                    } else {
                        life
                    }

                Text(
                    commanderOrLifeText.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(bottom = 2.dp)

                )

            }


        }

        Column {

        }

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
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Buttons(
                    onShortClick = {
                        decrementCounter()
                    },
                    onLongPressAction = {
                        decrementCounter()
                    },
                    iconID = R.drawable.botonminus,
                    contentDescription = "Decrement life",
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    getCounterValue().toString(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(16.dp)
                        .bounceClick()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    counterType = when (counterType) {
                                        CounterType.LIFE -> CounterType.COMMANDER_DAMAGE
                                        CounterType.COMMANDER_DAMAGE -> CounterType.POISON
                                        CounterType.POISON -> CounterType.ENERGY
                                        CounterType.ENERGY -> CounterType.LIFE
                                    }
                                },

                                onLongPress = {
                                    player.life = 40
                                    life = player.life
                                }
                            )
                        },
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
                        incrementCounter()
                    },
                    onLongPressAction = {
                        incrementCounter()

                    },
                    iconID = R.drawable.botonplus,
                    contentDescription = "Increment life",
                    modifier = Modifier
                )
            }
        }
    }
}
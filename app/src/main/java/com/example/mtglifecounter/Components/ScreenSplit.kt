package com.example.mtglifecounter.Components



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mtglifecounter.R
import com.example.mtglifecounter.ViewModel.GameViewModel

@Composable
fun ScreenSplitInFour(gameViewModel: GameViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            PlayerLifeCounter(
                player = gameViewModel.players[0],
                gameViewModel = gameViewModel,
                backgroundID = R.drawable.background6,
                rotateAngle = 180f,
                modifier = Modifier.weight(1f).fillMaxWidth()
            )

            PlayerLifeCounter(
                player = gameViewModel.players[1],
                gameViewModel = gameViewModel,
                backgroundID = R.drawable.background8,
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            PlayerLifeCounter(
                player = gameViewModel.players[2],
                gameViewModel = gameViewModel,
                backgroundID = R.drawable.background7,
                rotateAngle = 180f,
                modifier = Modifier.weight(1f).fillMaxWidth()
            )

            PlayerLifeCounter(
                player = gameViewModel.players[3],
                gameViewModel = gameViewModel,
                backgroundID = R.drawable.background4,
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }
    }
}

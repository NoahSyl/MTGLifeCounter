package com.example.mtglifecounter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.mtglifecounter.Components.ScreenSplitInFour
import com.example.mtglifecounter.ViewModel.GameViewModel
import com.example.mtglifecounter.ui.theme.MTGLifeCounterTheme

class MainActivity : ComponentActivity() {
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MTGLifeCounterTheme {
                ScreenSplitInFour(gameViewModel)
            }
        }
    }
}


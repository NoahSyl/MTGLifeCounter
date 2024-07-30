package com.example.mtglifecounter.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mtglifecounter.Data.Player

class GameViewModel : ViewModel() {
    var players = mutableStateListOf( //Lista de jugadores
        Player("Player 1", 40), //Nombre y vida inicial por defecto
        Player("Player 2", 40),
        Player("Player 3", 40),
        Player("Player 4", 40)
    )

    /*Añadir mas jugadores*/

    fun addPlayer() { //Añadir jugadores
        if (players.size < 6) {
            players.add(Player("Player ${players.size + 1}", 40))
        }
    }
}
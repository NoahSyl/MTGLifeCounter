package com.example.mtglifecounter.ViewModel

import android.view.View
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtglifecounter.Data.Player
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    //Variables para los autos
    private var autoIncrement: Job? = null
    private var autoDecrement: Job? = null

    //Lista de jugadores
    var players = mutableStateListOf( //Lista de jugadores
        Player("Player 1", 40), //Nombre y vida inicial por defecto
        Player("Player 2", 40),
        Player("Player 3", 40),
        Player("Player 4", 40)
    )

    /*Función para incrementar vida*/

    fun incrementLife(player: Player){
        player.life += 1

    }

    /*Función para decrementar vida*/

    fun decrementLife(player: Player){
        player.life -= 1
    }

    /*Función para incrementar vida automatica*/

    fun startIncrementingLife(player: Player) {
        autoIncrement?.cancel()
        autoIncrement = viewModelScope.launch {
            while (isActive) {
                incrementLife(player)
                delay(1000)
            }
        }
    }

    fun stopIncrementingLife(){
        autoIncrement?.cancel()
    }

    fun startDecrementingLife(player: Player){
        autoDecrement?.cancel()
        autoDecrement = viewModelScope.launch {
            while (true){
                decrementLife(player)
                delay(100)
            }
        }
    }

    fun stopDecrementingLife(){
        autoDecrement?.cancel()
    }


    /*Añadir mas jugadores*/

    fun addPlayer() { //Añadir jugadores
        if (players.size < 6) {
            players.add(Player("Player ${players.size + 1}", 40))
        }
    }
}
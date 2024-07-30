package com.example.mtglifecounter.Data

data class Player(
    var name: String,
    var life: Int,
    var commanderDamage: Int = 0,
    var poisonCounter: Int = 0,
    var energyCounter: Int = 0
)
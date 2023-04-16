package com.example.tictactoekotlin.model

import com.example.tictactoekotlin.enum.GameType

data class Game(
    var gameBoard: ArrayList<Field>,
    var gameType: GameType
)

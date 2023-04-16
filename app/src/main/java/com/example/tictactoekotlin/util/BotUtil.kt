package com.example.tictactoekotlin.util

import com.example.tictactoekotlin.model.Game

fun makeBotMove(game: Game) {
    game.playerTurn = true
}

private fun evaluate(game: Game): Int {
    val currentSymbol = game.symbolMove
    val opponentSymbol = getOppositeSymbol(game.symbolMove)
    return 1
}
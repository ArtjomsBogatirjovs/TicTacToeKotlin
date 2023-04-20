package com.example.tictactoekotlin.util

import com.example.tictactoekotlin.enum.GameStatus
import com.example.tictactoekotlin.enum.Symbol
import com.example.tictactoekotlin.model.Field
import com.example.tictactoekotlin.model.Game

private var botSymbol: Symbol? = null
private var gameClone: Game? = null
var bestField: Field? = null
fun getFieldToMove(game: Game): Int {
    botSymbol = game.symbolMove
    gameClone = game.clone()
    gameClone!!.gameBoard = cloneGameBoard(game.gameBoard)
    minimax(0, botSymbol!!, gameClone!!)
    return ((bestField!!.x - 1) * 3) + (bestField!!.y - 1) + 1
}

fun minimax(depth: Int, symbol: Symbol, game: Game): Int {

    if (isGameFinished(game)) {
        if (gameClone!!.gameStatus == GameStatus.DRAW) return 0
        if (gameClone!!.gameStatus == GameStatus.X_WON && botSymbol == Symbol.X) return 1
        if (gameClone!!.gameStatus == GameStatus.O_WON && botSymbol == Symbol.O) return 1
        if (gameClone!!.gameStatus == GameStatus.X_WON && botSymbol != Symbol.X) return -1
        if (gameClone!!.gameStatus == GameStatus.O_WON && botSymbol != Symbol.O) return -1
    }

    var min = Integer.MAX_VALUE
    var max = Integer.MIN_VALUE
    val availableFields = availableFields(game)
    for (i in availableFields.indices) {
        val field = availableFields[i]
        game.symbolMove = symbol
        if (symbol == botSymbol) {
            makeMove(null, field.x, field.y, game)
            val currentScore = minimax(depth + 1, getOppositeSymbol(botSymbol!!), game)
            max = Math.max(currentScore, max)

            if (currentScore >= 0) {
                if (depth == 0) bestField = field
            }

            if (currentScore == 1) {
                field.symbol = null
                break
            }

            if (i == availableFields.size - 1 && max < 0) {
                if (depth == 0) bestField = field
            }

        } else if (symbol != botSymbol) {
            makeMove(null, field.x, field.y, game)
            val currentScore = minimax(depth + 1, botSymbol!!, game)
            min = Math.min(currentScore, min)

            if (min == -1) {
                field.symbol = null
                break
            }
        }
        field.symbol = null
    }
    return if (symbol == botSymbol) max else min
}
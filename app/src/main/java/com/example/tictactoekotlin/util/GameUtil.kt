package com.example.tictactoekotlin.util

import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoekotlin.R
import com.example.tictactoekotlin.enum.GameStatus
import com.example.tictactoekotlin.enum.Symbol
import com.example.tictactoekotlin.model.Field
import com.example.tictactoekotlin.model.Game

private var winSymbol: Symbol? = null

fun createGameBoard(): ArrayList<Field> {
    val tempBoard = arrayListOf<Field>()
    for (i in 1..3) {
        for (j in 1..3) {
            tempBoard.add(Field(i, j, null))
        }
    }
    return tempBoard
}

fun cloneGameBoard(board: List<Field>): ArrayList<Field> {
    val tempBoard = arrayListOf<Field>()
    for (field in board) {
        tempBoard.add(Field(field.x, field.y, field.symbol))
    }
    return tempBoard
}

fun makeMove(but: AppCompatButton?, fieldX: Int, fieldY: Int, game: Game) {
    if (!isMoveAllowed(game, fieldX, fieldY)) {
        return
    }
    val field = getField(game, fieldX, fieldY)
    field.symbol = game.symbolMove

    if (but != null) {
        if (game.symbolMove == Symbol.X)
            but.setBackgroundResource(R.drawable.x)
        else
            but.setBackgroundResource(R.drawable.o)
    }

    game.symbolMove = getOppositeSymbol(game.symbolMove)
}

fun isMoveAllowed(game: Game, x: Int, y: Int): Boolean {
    return getField(game, x, y).symbol == null
}

fun getField(game: Game, x: Int, y: Int): Field {
    return game.gameBoard.find { field -> field.x == x && field.y == y }!!
}

fun getOppositeSymbol(symbol: Symbol): Symbol {
    return if (symbol == Symbol.X) Symbol.O else Symbol.X
}

fun availableFields(game: Game): List<Field> {
    val board = game.gameBoard
    return board.filter { field -> field.symbol == null }
}

fun isGameFinished(game: Game): Boolean {
    winSymbol = null
    isWinHorizontal(game)
    isWinVertical(game)
    isWinDiagonal(game)
    isWinDiagonal2(game)
    if (winSymbol != null) {
        game.gameStatus = if (winSymbol == Symbol.X) GameStatus.X_WON else GameStatus.O_WON
        return true
    }
    return isDraw(game)
}

private fun isWinHorizontal(game: Game) {
    for (i in 1..3) {
        val symbol = getField(game, i, 1).symbol ?: continue
        for (j in 2..3) {
            val symbolHorizontal = getField(game, i, j).symbol
            if (symbol != symbolHorizontal) {
                break
            }
            if (symbol == symbolHorizontal && j == 3) {
                winSymbol = symbol
            }
        }
    }
}

private fun isWinVertical(game: Game) {
    for (i in 1..3) {
        val symbol = getField(game, 1, i).symbol ?: continue
        for (j in 2..3) {
            val symbolVertical = getField(game, j, i).symbol
            if (symbol != symbolVertical) {
                break
            }
            if (symbol == symbolVertical && j == 3) {
                winSymbol = symbol
            }
        }
    }
}

private fun isWinDiagonal(game: Game) {
    val symbol = getField(game, 1, 1).symbol ?: return
    for (i in 2..3) {
        val symbolHorizontal = getField(game, i, i).symbol
        if (symbol != symbolHorizontal) {
            break
        }
        if (i == 3) {
            winSymbol = symbol
        }
    }
}

private fun isWinDiagonal2(game: Game) {
    val startSymbol = getField(game, 1, 3).symbol ?: return
    for (i in 2..3) {
        for (j in 1..3) {
            if (i + j != 4) {
                continue
            }
            val tempSymbol = getField(game, i, j).symbol
            if (startSymbol != tempSymbol) {
                return
            }
        }
    }
    winSymbol = startSymbol
}

private fun isDraw(game: Game): Boolean {
    for (i in 1..3) {
        for (j in 1..3) {
            getField(game, j, i).symbol ?: return false
        }
    }
    game.gameStatus = GameStatus.DRAW
    return true
}
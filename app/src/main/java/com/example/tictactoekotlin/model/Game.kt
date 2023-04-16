package com.example.tictactoekotlin.model

import android.os.Parcelable
import com.example.tictactoekotlin.enum.GameStatus
import com.example.tictactoekotlin.enum.GameType
import com.example.tictactoekotlin.enum.Symbol
import kotlinx.parcelize.Parcelize


@Parcelize
data class Game(
    var gameBoard: ArrayList<Field>,
    var gameType: GameType,
    var player: Player,
    val id: String,
    var playerTurn: Boolean,
    var symbolMove: Symbol = Symbol.X,
    var gameStatus: GameStatus
) : Parcelable

const val GAME_ATTRIBUTE = "game"
const val DATABASE_ATTRIBUTE = "games"
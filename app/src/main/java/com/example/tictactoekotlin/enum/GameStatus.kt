package com.example.tictactoekotlin.enum

enum class GameStatus(private val message: String) {
    IN_PROGRESS("Game in progress"),
    X_WON("Player X Won!"),
    O_WON("Player O Won!"),
    DRAW("Draw!");

    fun getMessage(): String {
        return message
    }
}
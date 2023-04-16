package com.example.tictactoekotlin.enum

enum class GameType(private val type: String) {
    PvP("Player vs Player"),
    PvE("Player vs Computer");

    fun getType(): String {
        return type
    }
}
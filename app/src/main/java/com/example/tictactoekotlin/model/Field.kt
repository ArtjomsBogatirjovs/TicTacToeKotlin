package com.example.tictactoekotlin.model

data class Field(
    var x: Int,
    var y: Int
) {
    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }
}

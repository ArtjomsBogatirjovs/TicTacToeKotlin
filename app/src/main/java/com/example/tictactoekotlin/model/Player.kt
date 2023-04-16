package com.example.tictactoekotlin.model

import androidx.annotation.Keep
import com.example.tictactoekotlin.enum.Symbol

@Keep
data class Player(var name: String, var symbol: Symbol?, var id: String) {

    fun getSymbol(): Symbol? {
        return symbol
    }

    fun setSymbol(symbol: Symbol) {
        this.symbol = symbol
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    constructor(name: String, id: String) : this(name, null, id)
}
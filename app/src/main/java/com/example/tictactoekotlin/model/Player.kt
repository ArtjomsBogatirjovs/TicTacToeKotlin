package com.example.tictactoekotlin.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.tictactoekotlin.enum.Symbol
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Player(var name: String, var symbol: Symbol?, var id: String) : Parcelable {
    constructor(name: String, id: String) : this(name, null, id)
}
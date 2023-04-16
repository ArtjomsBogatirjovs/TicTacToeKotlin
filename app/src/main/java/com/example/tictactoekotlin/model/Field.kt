package com.example.tictactoekotlin.model

import android.os.Parcelable
import com.example.tictactoekotlin.enum.Symbol
import kotlinx.parcelize.Parcelize

@Parcelize
data class Field(
    val x: Int,
    val y: Int,
    var symbol: Symbol?
) : Parcelable

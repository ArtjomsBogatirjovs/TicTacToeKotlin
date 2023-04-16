package com.example.tictactoekotlin.util

import android.app.AlertDialog
import android.content.Context

fun builderMessage(context: Context, message: String){
    val builder = AlertDialog.Builder(context)
    builder.setMessage(message)
    builder.setPositiveButton("OK") { dialog, _ ->
        dialog.dismiss()
    }
    builder.show()
}


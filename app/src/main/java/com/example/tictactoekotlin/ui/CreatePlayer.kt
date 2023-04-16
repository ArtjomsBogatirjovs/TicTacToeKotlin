package com.example.tictactoekotlin.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoekotlin.R
import com.google.firebase.database.FirebaseDatabase

class CreatePlayer : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance().getReference("players")

    //https://firebase.google.com/docs/android/setup?hl=ru
    //https://console.firebase.google.com/u/0/?hl=ru
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_player)

        val playerNameEditText = findViewById<EditText>(R.id.player_name)
        val continueButton = findViewById<Button>(R.id.button)

        continueButton.setOnClickListener {
            val playerName = playerNameEditText.text.toString()
            // save it to a database
        }
    }
}
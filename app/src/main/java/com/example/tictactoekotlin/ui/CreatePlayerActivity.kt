package com.example.tictactoekotlin.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoekotlin.R
import com.example.tictactoekotlin.model.Player
import com.google.firebase.database.FirebaseDatabase

class CreatePlayerActivity : AppCompatActivity(R.layout.activity_create_player) {
    private val database = FirebaseDatabase.getInstance().getReference("players")
    //https://firebase.google.com/docs/android/setup?hl=ru
    //https://console.firebase.google.com/u/0/?hl=ru

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playerNameEditText = findViewById<EditText>(R.id.player_name)
        val continueButton = findViewById<Button>(R.id.button)

        continueButton.setOnClickListener {
            val playerName = playerNameEditText.text.toString()
            if (playerName.isBlank()) {
                Toast.makeText(applicationContext, "Enter your name", Toast.LENGTH_SHORT).show()
            } else {
                playerNameShown = playerName
                val id = database.push().key!!
                val player = Player(playerName.trim(), id)
                savePlayer(player)
                database.child(id).setValue(player).addOnCompleteListener {
                    finish()
                }
            }
        }
    }
    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}



private fun Context.savePlayer(player: Player) =
    getSharedPreferences("settings", Context.MODE_PRIVATE).edit().apply {
        putString("name", player.name)
        putString("id", player.id)
    }.apply()

fun Context.getPlayer(): Player? {
    val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
    val name = prefs.getString("name", null)
    val id = prefs.getString("id", null)
    return if (name == null || id == null)
        null
    else Player(name, id)
}
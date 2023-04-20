package com.example.tictactoekotlin.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoekotlin.R
import com.example.tictactoekotlin.enum.GameStatus
import com.example.tictactoekotlin.enum.GameType
import com.example.tictactoekotlin.enum.Symbol
import com.example.tictactoekotlin.model.*
import com.example.tictactoekotlin.util.createGameBoard
import com.google.firebase.database.FirebaseDatabase

var playerNameShown: String? = null

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val database = FirebaseDatabase.getInstance().getReference(DATABASE_ATTRIBUTE)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val playerName = findViewById<TextView>(R.id.player_name)
        if (getPlayer() == null || playerNameShown == null) {
            startActivity(Intent(this, CreatePlayerActivity::class.java))
        }
        val player: Player? = getPlayer()

        if (playerNameShown != null) {
            playerName.text = playerNameShown
        }

        var symbol: Symbol? = null

        val symbolView = findViewById<TextView>(R.id.symbol)
        val symbolO = findViewById<Button>(R.id.buttonO)
        val symbolX = findViewById<Button>(R.id.buttonX)

        symbolO.setOnClickListener {
            symbolView.text = "You play as " + Symbol.O
            symbol = Symbol.O
        }

        symbolX.setOnClickListener {
            symbolView.text = "You play as " + Symbol.X
            symbol = Symbol.X
        }

        val playLocal = findViewById<AppCompatButton>(R.id.play_with_friend)
        val playVsAi = findViewById<AppCompatButton>(R.id.play_with_computer)
        val exit = findViewById<AppCompatButton>(R.id.exit)

        playLocal.setOnClickListener {
            if (player != null) {
                player.symbol = Symbol.X
                val id = database.push().key!!
                val newGame = Game(
                    createGameBoard(),
                    GameType.PvP,
                    player,
                    id,
                    true,
                    Symbol.X,
                    GameStatus.IN_PROGRESS
                )
                database.child(id).setValue(newGame)
                val intent = Intent(this, GameActivity::class.java).apply {
                    putExtra(GAME_ATTRIBUTE, newGame)
                }
                startActivity(intent)
            }
        }

        playVsAi.setOnClickListener {
            if (symbol == null) {
                Toast.makeText(applicationContext, "Choose symbol", Toast.LENGTH_SHORT).show()
            } else if (player != null) {
                player.symbol = symbol
                val id = database.push().key!!
                val newGame = Game(
                    createGameBoard(),
                    GameType.PvE,
                    player,
                    id,
                    player.symbol == Symbol.X,
                    Symbol.X,
                    GameStatus.IN_PROGRESS
                )
                database.child(id).setValue(newGame)
                val intent = Intent(this, GameActivity::class.java).apply {
                    putExtra(GAME_ATTRIBUTE, newGame)
                }
                startActivity(intent)
            }
        }
        exit.setOnClickListener { finish() }
    }

    fun openLink(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ArtjomsBogatirjovs"))
        startActivity(intent)
    }

    fun changeName(view: View) {
        getSharedPreferences("settings", Context.MODE_PRIVATE).edit().clear().apply()
        playerNameShown = null
        recreate()
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
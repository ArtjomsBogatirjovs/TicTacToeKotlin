package com.example.tictactoekotlin.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoekotlin.R
import com.example.tictactoekotlin.enum.GameStatus
import com.example.tictactoekotlin.enum.GameType
import com.example.tictactoekotlin.model.GAME_ATTRIBUTE
import com.example.tictactoekotlin.model.Game
import com.example.tictactoekotlin.util.*

class GameActivity : AppCompatActivity(R.layout.activity_game) {

    private lateinit var game: Game

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            game = intent.getParcelableExtra(GAME_ATTRIBUTE)!!
        } catch (e: Exception) {
            builderMessage(this, e.message!!)
            finish()
        }
        if (!game.playerTurn) {
            val buttonId = "field" + getFieldToMove(game)
            val button = findButtonByText(buttonId)
            makeMove(button, bestField!!.x, bestField!!.y, game)
            game.playerTurn = true
        }
    }

    fun onClick(view: View) {
        if (game.gameStatus != GameStatus.IN_PROGRESS) {
            builderMessage(this, game.gameStatus.getMessage())
            return
        }
        val but = view as AppCompatButton
        var fieldX = 0
        var fieldY = 0
        if (game.playerTurn) {
            when (but.id) {
                R.id.field1 -> {
                    fieldY = 1
                    fieldX = 1
                }
                R.id.field2 -> {
                    fieldY = 2
                    fieldX = 1
                }
                R.id.field3 -> {
                    fieldY = 3
                    fieldX = 1
                }
                R.id.field4 -> {
                    fieldY = 1
                    fieldX = 2
                }
                R.id.field5 -> {
                    fieldY = 2
                    fieldX = 2
                }
                R.id.field6 -> {
                    fieldY = 3
                    fieldX = 2
                }
                R.id.field7 -> {
                    fieldY = 1
                    fieldX = 3
                }
                R.id.field8 -> {
                    fieldY = 2
                    fieldX = 3
                }
                R.id.field9 -> {
                    fieldY = 3
                    fieldX = 3
                }
            }

            if (!isMoveAllowed(game, fieldX, fieldY)) {
                return
            }

            makeMove(but, fieldX, fieldY, game)

            if (isGameFinished(game)) {
                builderMessage(this, game.gameStatus.getMessage())
                return
            }

            if (game.gameType == GameType.PvE) {
                game.playerTurn = false
                val buttonId = "field" + getFieldToMove(game)
                val button = findButtonByText(buttonId)
                makeMove(button, bestField!!.x, bestField!!.y, game)
                game.playerTurn = true
            }

            if (isGameFinished(game)) {
                builderMessage(this, game.gameStatus.getMessage())
                return
            }
        } else {
            builderMessage(this, "Not your turn!")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    @SuppressLint("DiscouragedApi")
    private fun findButtonByText(id: String): AppCompatButton? {
        val rootLayout = findViewById<View>(android.R.id.content)
        if (rootLayout != null) {
            val resId = resources.getIdentifier(
                id,
                "id",
                packageName
            )
            return findViewById(resId)
        }
        return null
    }
}


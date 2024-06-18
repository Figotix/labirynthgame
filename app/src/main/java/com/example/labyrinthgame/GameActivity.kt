package com.example.labyrinthgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var buttonUp: Button
    private lateinit var buttonLeft: Button
    private lateinit var buttonRight: Button
    private lateinit var buttonDown: Button
    private lateinit var roomText: TextView

    private val labyrinth = arrayOf(
        intArrayOf(10, 8, 10, 9),
        intArrayOf(28, 1, 0, 12),
        intArrayOf(12, 10, 9, 13),
        intArrayOf(6, 5, 6, 5)
    )

    private var currentX = 0
    private var currentY = 0
    private var moveCount = 0  // Liczba ruchów

    //Tworzenie aktywności, ustawia początkowy stan gry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        buttonUp = findViewById(R.id.buttonUp)
        buttonLeft = findViewById(R.id.buttonLeft)
        buttonRight = findViewById(R.id.buttonRight)
        buttonDown = findViewById(R.id.buttonDown)
        roomText = findViewById(R.id.roomText)

        updateRoom() //Aktualizacja stanu bieżącego pokoju

        //Ruchy gracza
        buttonUp.setOnClickListener { move(0, -1) }
        buttonLeft.setOnClickListener { move(-1, 0) }
        buttonRight.setOnClickListener { move(1, 0) }
        buttonDown.setOnClickListener { move(0, 1) }
    }

    private fun updateRoom() {
        val room = labyrinth[currentY][currentX]
        roomText.text = "Room ($currentX, $currentY)"

        buttonUp.isEnabled = room and 4 != 0
        buttonLeft.isEnabled = room and 1 != 0
        buttonRight.isEnabled = room and 2 != 0
        buttonDown.isEnabled = room and 8 != 0

        if (room == 0) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("MOVE_COUNT", moveCount)  // Przekazanie wyniku
            startActivity(intent)
            finish()
        }
    }

    private fun move(dx: Int, dy: Int) {
        currentX += dx
        currentY += dy
        moveCount++  // Zwiększenie liczby ruchów
        updateRoom()
    }
}

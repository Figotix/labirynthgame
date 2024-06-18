package com.example.labyrinthgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var restartButton: Button
    private lateinit var resultText: TextView  // Dodanie TextView dla wyniku

    //Wywołuje akcje po naciśnięciu przycisku restartu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val moveCount = intent.getIntExtra("MOVE_COUNT", 0)  // Pobranie wyniku

        resultText = findViewById(R.id.resultText)
        resultText.text = "Gratulacje! Pokonałeś labirynt w $moveCount ruchach."

        restartButton = findViewById(R.id.restartButton)
        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

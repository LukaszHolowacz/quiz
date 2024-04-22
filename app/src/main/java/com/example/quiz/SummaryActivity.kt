package com.example.quiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val scorePercentage = (correctAnswers * 100.0 / totalQuestions).toInt()

        val resultsTextView: TextView = findViewById(R.id.resultsTextView)
        resultsTextView.text = "Poprawne odpowiedzi: $correctAnswers\n" +
                "Wynik: $scorePercentage%"

        val messageTextView: TextView = findViewById(R.id.textView)
        if (correctAnswers == totalQuestions) {
            messageTextView.text = "Brawo, wygrałeś."
            messageTextView.setTextColor(Color.GREEN)
        } else {
            messageTextView.text = "Niestety nie udało ci się wygrać."
            messageTextView.setTextColor(Color.RED)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

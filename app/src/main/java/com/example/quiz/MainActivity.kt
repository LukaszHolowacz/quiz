package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.io.Serializable

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val imageResId: Int? = null
) : Serializable


class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        Question(
            questionText = "Jaka marka auta znajduje się na zdjęciu powyżej?",
            options = listOf("Audi", "BMW", "Lotus", "TATA"),
            correctAnswerIndex = 1,
            imageResId = R.drawable.bmw
        ),
        Question(
            questionText = "Z jakiego państwa pochodzi firma TATA?",
            options = listOf("Indie", "Niemcy", "Australia", "Stany Zjednoczone"),
            correctAnswerIndex = 0
        ),
        Question(
            questionText = "Która marka auta oznacza 'auto dla ludu'?",
            options = listOf("Fiat", "Porsche", "Opel", "Volkswagen"),
            correctAnswerIndex = 3
        ),
        Question(
            questionText = "Która marka nazywa swoje modele od nazw wysp rodzimego kraju?",
            options = listOf("Kia", "Seat", "Hyundai", "Volvo"),
            correctAnswerIndex = 1
        ),
        Question(
            questionText = "Która marka aut pochodzi od hiszpańskiego imienia?",
            options = listOf("Mercedes", "Ford", "Nissan", "Volvo"),
            correctAnswerIndex = 0
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.startQuizButton).setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("questions", ArrayList(questions))
            startActivity(intent)
        }
    }
}

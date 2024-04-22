package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class QuestionActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private var selectedAnswers = mutableListOf<Int?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        questions = intent.getSerializableExtra("questions") as List<Question>

        selectedAnswers = MutableList(questions.size) { null }

        setupQuestionView(currentQuestionIndex)

        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        previousButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                saveCurrentAnswer()
                currentQuestionIndex--
                setupQuestionView(currentQuestionIndex)
            }
        }

        nextButton.setOnClickListener {
            saveCurrentAnswer()

            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                setupQuestionView(currentQuestionIndex)
            } else {
                goToSummary()
            }

            if (currentQuestionIndex == questions.size - 1) {
                nextButton.text = getString(R.string.finish_quiz)
            } else {
                nextButton.text = getString(R.string.next_question)
            }
        }
    }

    private fun setupQuestionView(questionIndex: Int) {
        val imageView: ImageView = findViewById(R.id.imageView)
        val questionText: TextView = findViewById(R.id.pytanie)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val question = questions[questionIndex]

        questionText.text = question.questionText
        question.imageResId?.let {
            imageView.setImageResource(it)
            imageView.visibility = View.VISIBLE
        } ?: run {
            imageView.visibility = View.GONE
        }

        val radioButtonIds = listOf(R.id.radioButtonA, R.id.radioButtonB, R.id.radioButtonC, R.id.radioButtonD)
        question.options.forEachIndexed { index, option ->
            val radioButton: RadioButton = findViewById(radioButtonIds[index])
            radioButton.text = option
        }

        selectedAnswers[questionIndex]?.let { savedAnswerIndex ->
            radioGroup.check(radioButtonIds[savedAnswerIndex])
        } ?: radioGroup.clearCheck()
    }

    private fun saveCurrentAnswer() {
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val checkedRadioButtonId = radioGroup.checkedRadioButtonId
        if (checkedRadioButtonId != -1) {
            val radioButton: RadioButton = findViewById(checkedRadioButtonId)
            val answerIndex = radioGroup.indexOfChild(radioButton)
            selectedAnswers[currentQuestionIndex] = answerIndex
            if (questions[currentQuestionIndex].correctAnswerIndex == answerIndex) {
                correctAnswers++
            } else {
                correctAnswers--
            }
        }
    }

    private fun goToSummary() {
        val intent = Intent(this, SummaryActivity::class.java).apply {
            putExtra("totalQuestions", questions.size)
            putExtra("correctAnswers", correctAnswers)
        }
        startActivity(intent)
        finish()
    }
}

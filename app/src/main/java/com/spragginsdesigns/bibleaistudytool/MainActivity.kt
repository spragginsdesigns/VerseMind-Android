package com.spragginsdesigns.bibleaistudytool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import io.noties.markwon.Markwon
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var etBibleQuestion: EditText
    private lateinit var btnSubmit: MaterialButton
    private lateinit var tvQuestion: TextView
    private lateinit var tvAnswer: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var questionCard: MaterialCardView
    private lateinit var answerCard: MaterialCardView
    private val client = OkHttpClient()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        runOnUiThread {
            showError("Error: ${throwable.localizedMessage ?: "Unknown error"}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        etBibleQuestion = findViewById(R.id.etBibleQuestion)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvAnswer = findViewById(R.id.tvAnswer)
        progressBar = findViewById(R.id.progressBar)
        questionCard = findViewById(R.id.questionCard)
        answerCard = findViewById(R.id.answerCard)

        // Initially hide the question and answer cards
        questionCard.visibility = View.GONE
        answerCard.visibility = View.GONE
    }

    private fun setupClickListeners() {
        btnSubmit.setOnClickListener {
            val question = etBibleQuestion.text.toString().trim()
            if (question.isNotEmpty()) {
                getAnswer(question)
            } else {
                showError("Please enter a question.")
            }
        }
    }

    private fun getAnswer(question: String) {
        showLoading(true)
        tvQuestion.text = question
        questionCard.visibility = View.VISIBLE
        answerCard.visibility = View.GONE

        CoroutineScope(Dispatchers.Main + coroutineExceptionHandler).launch {
            try {
                val answer = withContext(Dispatchers.IO) {
                    fetchAnswerFromAPI(question)
                }
                displayMarkdownAnswer(answer)
                answerCard.visibility = View.VISIBLE
            } catch (e: Exception) {
                showError("Error: ${e.localizedMessage ?: "An error occurred"}")
            } finally {
                showLoading(false)
            }
        }
    }

    private fun displayMarkdownAnswer(markdown: String) {
        val markwon = Markwon.create(this)
        markwon.setMarkdown(tvAnswer, markdown)
    }

    private suspend fun fetchAnswerFromAPI(question: String): String {
        val url = "https://bible-ai-explorer.vercel.app/api/ask-question"
        val jsonBody = JSONObject().put("question", question).toString()
        val request = Request.Builder()
            .url(url)
            .post(jsonBody.toRequestBody("application/json".toMediaType()))
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected response code: ${response.code}")
                val responseBody = response.body?.string() ?: throw IOException("Empty response body")
                JSONObject(responseBody).getString("response")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        btnSubmit.isEnabled = !isLoading
        etBibleQuestion.isEnabled = !isLoading
    }

    private fun showError(message: String) {
        tvAnswer.text = message
        answerCard.visibility = View.VISIBLE
    }
}
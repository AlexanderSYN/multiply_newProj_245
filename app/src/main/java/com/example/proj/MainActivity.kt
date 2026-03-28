package com.example.proj

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proj.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var n1: Int = 0
    var n2: Int = 0
    var correctly: Int = 0
    var uncorrectly: Int = 0
    var score: Int = 0
    var answer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generateNumbers()
        binding.btnCheckAnsw.setOnClickListener {
            val answerByUser = binding.etAnsw.text.toString().toInt()
            if (answerByUser == answer) {
                correctly += 1
                score += 150
                updateStats()
                Toast.makeText(this, "Верно!", Toast.LENGTH_LONG).show()
            } else {
                uncorrectly += 1

                if (score >= 150)
                    score -= 150
                else if (score >= 50)
                    score -= 50
                else
                    Toast.makeText(this, "Слишком мало очков!", Toast.LENGTH_LONG).show()

                updateStats()
                Thread.sleep(1000)
                Toast.makeText(this, "Неверно, правильный ответ был: $answer!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun generateNumbers() {
        n1 = Random.nextInt(2, 10)
        n2 = Random.nextInt(2, 10)
        binding.tvQuestion.text = "$n1 x $n2 = "
        answer = n1 * n2
    }

    private fun updateStats() {
        binding.tvCorrectlyAnsw.text = "Правильные ответы: $correctly"
        binding.tvCorrectlyAnsw.text = "Неправильные ответы: $uncorrectly"
        binding.tvScore.text = "Баллы: $score"
    }
}
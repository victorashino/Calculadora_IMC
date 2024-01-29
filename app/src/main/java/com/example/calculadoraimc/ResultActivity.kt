package com.example.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadoraimc.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            onBackPressed()
            finish()
        }

    val result = intent.getFloatExtra("EXTRA_RESULT", 0.1f)
        binding.textResult.text = "%.1f".format(result)

        val classification = if (result < 18.5f) {
            "ABAIXO DO PESO"
        } else if (result in 18.5f..24.9f) {
            "NORMAL"
        } else if (result in 25f..29.9f) {
            "SOBREPESO"
        } else if (result in 30f..39.9f) {
            "OBESIDADE"
        } else {
            "OBESIDADE GRAVE"
        }

        binding.textClassification.text = classification
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@ResultActivity.overridePendingTransition(
            R.anim.animate_fade_enter,
            R.anim.animate_slide_out_right
        )
    }
}
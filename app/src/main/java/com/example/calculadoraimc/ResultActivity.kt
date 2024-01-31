package com.example.calculadoraimc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraimc.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val viewModel: ResultViewModel by viewModels()

    companion object {

        private const val EXTRA_RESULT = "extra.result"

        fun start(context: Context, imc: Float): Intent {
            return Intent(context, ResultActivity::class.java)
                .apply {
                    putExtra(EXTRA_RESULT, imc)
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            onBackPressed()
            finish()
        }

        val result = intent.getFloatExtra(EXTRA_RESULT, 0.1f)

        binding.textResult.text = viewModel.returnResult(result)
        binding.textClassification.text = viewModel.returnClassification(result)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@ResultActivity.overridePendingTransition(
            R.anim.animate_fade_enter,
            R.anim.animate_slide_out_right
        )
    }
}
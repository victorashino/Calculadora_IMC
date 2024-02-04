package com.example.calculadoraimc.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraimc.R
import com.example.calculadoraimc.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_WEIGHT = "extra.weight"
        private const val EXTRA_HEIGHT = "extra.height"

        fun start(context: Context, weight: Float, height: Float): Intent {
            return Intent(context, ResultActivity::class.java)
                .apply {
                    putExtra(EXTRA_WEIGHT, weight)
                    putExtra(EXTRA_HEIGHT, height)
                }
        }
    }

    private lateinit var binding: ActivityResultBinding

    private val viewModel: ResultViewModel by viewModels {
        ResultViewModel.getVMFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            onBackPressed()
            finish()
        }

        val weight = intent.getFloatExtra(EXTRA_WEIGHT, 0.0f)
        val height = intent.getFloatExtra(EXTRA_HEIGHT, 0.0f)
        val imc = viewModel.returnIMC(0, weight, height)

        binding.textResult.text = imc.imc
        binding.textClassification.text = imc.classification

        viewModel.createItem(imc)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@ResultActivity.overridePendingTransition(
            R.anim.animate_fade_enter,
            R.anim.animate_slide_out_right
        )
    }
}
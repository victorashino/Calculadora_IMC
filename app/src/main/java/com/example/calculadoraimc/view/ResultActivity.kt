package com.example.calculadoraimc.view

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraimc.R
import com.example.calculadoraimc.databinding.ActivityResultBinding
import com.example.calculadoraimc.view.viewmodel.ResultViewModel

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
    private val viewModel: ResultViewModel by viewModels { ResultViewModel.getVMFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            val anim = ActivityOptions.makeCustomAnimation(
                applicationContext,
                R.anim.animate_fade_enter,
                R.anim.animate_slide_out_right
            ).toBundle()
            startActivity(intent, anim)
            finish()
        }

        val weight = intent.getFloatExtra(EXTRA_WEIGHT, 0.0f)
        val height = intent.getFloatExtra(EXTRA_HEIGHT, 0.0f)
        val imc = viewModel.returnIMC(0, weight, height)

        binding.textResult.text = imc.imc
        binding.textClassification.text = imc.classification

        viewModel.createItem(imc)
    }
}
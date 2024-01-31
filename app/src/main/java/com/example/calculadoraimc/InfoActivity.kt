package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraimc.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    private val viewModel: InfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }

        val btnCalculate = binding.buttonCalculate
        val weight = binding.editWeight
        val height = binding.editHeight

        btnCalculate.setOnClickListener {
            if (height.text.toString().isNotEmpty() && weight.text.toString().isNotEmpty()) {
                startActivity(
                    ResultActivity.start(
                            this,
                            viewModel.returnIMC(
                                weight.text.toString().toFloat(),
                                height.text.toString().toFloat()
                            )
                        )
                )
            } else {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        this@InfoActivity.overridePendingTransition(
            R.anim.animate_slide_left_enter,
            R.anim.animate_fade_exit
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@InfoActivity.overridePendingTransition(
            R.anim.animate_fade_enter,
            R.anim.animate_slide_out_right
        )
    }
}
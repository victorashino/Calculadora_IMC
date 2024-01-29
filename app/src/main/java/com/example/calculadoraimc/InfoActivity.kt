package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraimc.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }

        val btnCalculate = binding.buttonCalculate

        val userWeight = binding.editWeight
        val userHeight = binding.editHeight

        btnCalculate.setOnClickListener {

            val heightStr = userHeight.text.toString()
            val weightStr = userWeight.text.toString()

            if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
                val height = heightStr.toFloat()
                val weight = weightStr.toFloat()
                val imc = weight / (height * height)

                val intent = Intent(this, ResultActivity::class.java)
                    .apply {
                        putExtra("EXTRA_RESULT", imc)
                    }
                startActivity(intent)
                this@InfoActivity.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_fade_exit
                )
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        this@InfoActivity.overridePendingTransition(
            R.anim.animate_fade_enter,
            R.anim.animate_slide_out_right
        )
    }

}
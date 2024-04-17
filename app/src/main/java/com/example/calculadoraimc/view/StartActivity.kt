package com.example.calculadoraimc.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadoraimc.R
import com.example.calculadoraimc.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {

            val intent = Intent(this, InfoActivity::class.java)
            val anim = ActivityOptions.makeCustomAnimation(
                applicationContext,
                R.anim.animate_slide_left_enter,
                R.anim.animate_fade_exit
            ).toBundle()
            startActivity(intent, anim)
        }
    }
}

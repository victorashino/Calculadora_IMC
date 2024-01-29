package com.example.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.calculadoraimc.databinding.ActivityInfoBinding
import com.example.calculadoraimc.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
            this@StartActivity.overridePendingTransition(
                R.anim.animate_slide_left_enter,
                R.anim.animate_fade_exit
            )
        }
    }
}

package com.example.calculadoraimc.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraimc.R
import com.example.calculadoraimc.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val weight = binding.editWeight
        val height = binding.editHeight

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            val anim = ActivityOptions.makeCustomAnimation(
                applicationContext,
                R.anim.animate_fade_enter,
                R.anim.animate_slide_out_right
            ).toBundle()
            startActivity(intent, anim)
        }

        binding.buttonCalculate.setOnClickListener {
            if (height.text.toString().isNotEmpty() && weight.text.toString().isNotEmpty()) {
                val weightStr = weight.text.toString().toFloat()
                val heightStr = height.text.toString().toFloat()
                startActivity(
                    ResultActivity.start(this, weightStr, heightStr)
                )
            } else {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history -> {
                val intent = Intent(HistoryActivity.start(applicationContext))
                val anim = ActivityOptions.makeCustomAnimation(
                    applicationContext,
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_fade_exit
                ).toBundle()
                startActivity(intent, anim)
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}
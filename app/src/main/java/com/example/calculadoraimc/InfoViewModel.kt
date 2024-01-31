package com.example.calculadoraimc

import android.widget.EditText
import androidx.lifecycle.ViewModel

class InfoViewModel : ViewModel() {

    fun returnIMC(weight: EditText, height: EditText): Float {
        return weight.text.toString().toFloat() /
                (height.text.toString().toFloat() * height.text.toString().toFloat())
    }
}
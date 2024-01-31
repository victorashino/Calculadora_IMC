package com.example.calculadoraimc

import android.widget.EditText
import androidx.lifecycle.ViewModel

class InfoViewModel : ViewModel() {

    fun returnIMC(weight: Float, height: Float): Float {
        return weight / (height * height)
    }
}
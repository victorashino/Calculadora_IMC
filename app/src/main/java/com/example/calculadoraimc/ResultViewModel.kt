package com.example.calculadoraimc

import android.widget.TextView
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {

    fun returnResult(imc: Float): String {
        return "%.1f".format(imc)
    }

    fun returnClassification(result: Float): String {
        return if (result < 18.5f) {
            "ABAIXO DO PESO"
        } else if (result in 18.5f..24.9f) {
            "NORMAL"
        } else if (result in 25f..29.9f) {
            "SOBREPESO"
        } else if (result in 30f..39.9f) {
            "OBESIDADE"
        } else {
            "OBESIDADE GRAVE"
        }
    }

}
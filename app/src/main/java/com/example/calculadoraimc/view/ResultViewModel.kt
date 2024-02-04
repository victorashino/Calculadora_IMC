package com.example.calculadoraimc.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.calculadoraimc.ImcApplication
import com.example.calculadoraimc.data.HistoryDao
import com.example.calculadoraimc.domain.IMC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultViewModel(
    private val dao: HistoryDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    companion object {

        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val dataBaseInstance = (application as ImcApplication).getAppDataBase()
            val dao = dataBaseInstance.historyDao()
            val factory = object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ResultViewModel(dao) as T
                }
            }
            return factory
        }
    }

    fun createItem(imc: IMC) {
        viewModelScope.launch(dispatcher) {
            dao.insert(imc)
        }
    }

    fun returnIMC(id: Int, weight: Float, height: Float): IMC {
        val imc = weight / (height * height)
        val classification = returnClassification(imc)

        val imcStr = "%.1f".format(imc)
        val weightStr = weight.toString()
        val heightStr = height.toString()

        return IMC(
            id,
            weightStr,
            heightStr,
            classification,
            imcStr
        )
    }

    private fun returnClassification(result: Float): String {
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
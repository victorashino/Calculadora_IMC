package com.example.calculadoraimc.view.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadoraimc.ImcApplication
import com.example.calculadoraimc.data.HistoryDao
import com.example.calculadoraimc.domain.IMC
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyDao: HistoryDao
) : ViewModel() {

    companion object {

        fun create(application: Application): HistoryViewModel {
            val databaseInstance = (application as ImcApplication).getAppDataBase()
            val dao = databaseInstance.historyDao()
            return HistoryViewModel(dao)
        }
    }

    val historyLiveData: LiveData<List<IMC>> = historyDao.getAll()

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            historyDao.delete(id)
        }
    }
}

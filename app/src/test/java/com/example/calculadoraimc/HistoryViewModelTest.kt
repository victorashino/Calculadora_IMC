package com.example.calculadoraimc

import com.example.calculadoraimc.data.HistoryDao
import com.example.calculadoraimc.domain.IMC
import com.example.calculadoraimc.view.HistoryViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class HistoryViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val historyDao: HistoryDao = mock()

    private val underTest: HistoryViewModel by lazy {
        HistoryViewModel(historyDao)
    }

    @Test
    fun deleteItem() = runTest {
        val imc = IMC(
            id = 1,
            weight = "60.0",
            height = "1.77",
            classification = "NORMAL",
            imc = "19.2"
        )

        underTest.deleteItem(imc.id)

        verify(historyDao).delete(imc.id)
    }

}
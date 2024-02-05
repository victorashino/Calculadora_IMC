package com.example.calculadoraimc

import com.example.calculadoraimc.data.HistoryDao
import com.example.calculadoraimc.domain.IMC
import com.example.calculadoraimc.view.ResultViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.random.Random

class ResultViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val historyDao: HistoryDao = mock()

    private val underTest: ResultViewModel by lazy {
        ResultViewModel(historyDao)
    }

    @Test
    fun createItem() = runTest {
        val imc = IMC(
            id = 1,
            weight = "60.0",
            height = "1.77",
            classification = "NORMAL",
            imc = "19.2"
        )

        underTest.createItem(imc)

        verify(historyDao).insert(imc)
    }

    @Test
    fun returnIMC() = runTest {
        val id = 0
        val weight = Random.nextFloat()
        val height = Random.nextFloat()
        val imc = weight / (height * height)
        val classification = underTest.returnClassification(imc)

        val weightStr = weight.toString()
        val heightStr = height.toString()
        val imcStr = "%.1f".format(imc)

        val result = underTest.returnIMC(id, weight, height)

        val expected = IMC(id, weightStr, heightStr, classification, imcStr)

        assert(expected.imc == result.imc)
    }

    @Test
    fun returnClassification() = runTest  {
        // Given
        val imc = Random.nextFloat()

        // When
        val result = underTest.returnClassification(imc)

        // Then
        val expected = if (imc < 18.5f) {
            "ABAIXO DO PESO"
        } else if (imc in 18.5f..24.9f) {
            "NORMAL"
        } else if (imc in 25f..29.9f) {
            "SOBREPESO"
        } else if (imc in 30f..39.9f) {
            "OBESIDADE"
        } else {
            "OBESIDADE GRAVE"
        }

        assert(expected == result)

    }
}
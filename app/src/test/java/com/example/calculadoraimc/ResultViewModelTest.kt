package com.example.calculadoraimc

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.random.Random

class ResultViewModelTest {

    private val underTest: ResultViewModel by lazy {
        ResultViewModel()
    }

    @Test
    fun returnResult() {
        // Given
        val imc = Random.nextFloat()

        // When
        val result = underTest.returnResult(imc)

        // Then
        val expected = "%.1f".format(imc)

        assert(expected == result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun returnClassification(): Unit = runTest  {
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
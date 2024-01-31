package com.example.calculadoraimc

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.random.Random

class InfoViewModelTest {

    private val underTest: InfoViewModel by lazy {
        InfoViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun returnIMC(): Unit = runTest {
        // Given
        val weight = Random.nextFloat()
        val height = Random.nextFloat()

        // When
        val result = underTest.returnIMC(weight, height)

        // Then
        val expected = weight / (height * height)
        assert(expected == result)
    }

}
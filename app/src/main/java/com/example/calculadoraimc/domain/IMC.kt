package com.example.calculadoraimc.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class IMC (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val weight: String,
    val height: String,
    val classification: String,
    val imc: String
) : Serializable

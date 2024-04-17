package com.example.calculadoraimc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculadoraimc.domain.IMC

@Database(entities = [IMC::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
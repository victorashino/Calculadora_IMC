package com.example.calculadoraimc

import android.app.Application
import androidx.room.Room
import com.example.calculadoraimc.data.AppDataBase

class ImcApplication : Application() {

    private lateinit var database: AppDataBase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "historyimc-database"
        ).build()
    }

    fun getAppDataBase(): AppDataBase {
        return database
    }

}
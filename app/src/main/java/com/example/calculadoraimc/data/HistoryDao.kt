package com.example.calculadoraimc.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.calculadoraimc.domain.IMC

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imc: IMC)

    @Query("SELECT * FROM imc")
    fun getAll(): LiveData<List<IMC>>

    @Query("DELETE FROM imc WHERE id =:id")
    suspend fun delete(id: Int)

}
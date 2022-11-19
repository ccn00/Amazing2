package com.example.amazing2.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.amazing2.data.LineaCompra

@Dao
interface LineaCompraDAO {
    // Hacemos una consulta para obtener todas las lineas de compra
    @Query("SELECT * FROM LineaCompra")
    fun getAll(): List<LineaCompra>

    @Insert
    fun insert(lineaCompra: LineaCompra)

    @Update
    fun update(lineaCompra: LineaCompra)

    @Delete
    fun delete(lineaCompra: LineaCompra)

}
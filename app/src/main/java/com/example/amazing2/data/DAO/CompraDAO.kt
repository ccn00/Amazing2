package com.example.amazing2.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.amazing2.data.Cliente
import com.example.amazing2.data.Compra

@Dao
interface CompraDAO {
    // Hacemos una consulta para obtener todas las compras
    @Query("SELECT * FROM Compra")
    fun getAll(): List<Compra>


    // Obtenemos una compra por su id
    @Query("SELECT * FROM Compra WHERE id = :id")
    fun getById(id: Int): Compra

    // Obtenemos una compra por su idCliente
    @Query("SELECT * FROM Compra WHERE idCliente = :idCliente")
    fun getByCliente(idCliente: Int): List<Compra>

    // Obtenemos una compra por su idCliente y fecha de compra
    @Query("SELECT * FROM Compra WHERE idCliente = :idCliente AND fechaCompra = :fecha")
    fun getByClienteFecha(idCliente: Int, fecha: String): Compra

    // Hacemos una consulta para insertar una compra
    @Insert
    fun insert(compra: Compra)

    // Hacemos una consulta para actualizar una compra
    @Update
    fun update(compra: Compra)

    // Hacemos una consulta para eliminar una compra
    @Delete
    fun delete(compra: Compra)

}
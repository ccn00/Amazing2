package com.example.amazing2.data.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.amazing2.data.Categoria

@Dao
interface CategoriaDAO {
    // Hacemos una consulta para obtener todas las categorias
    @Query("SELECT * FROM Categoria")
    fun getAll(): List<Categoria>

    // Hacemos una consulta para obtener una categoria por su id
    @Query("SELECT * FROM Categoria WHERE id = :id")
    fun getById(id: Int): Categoria

    // Hacemos una consulta para obtener una categoria por su nombre
    @Query("SELECT * FROM Categoria WHERE nombre = :nombre")
    fun getByNombre(nombre: String): Categoria

}
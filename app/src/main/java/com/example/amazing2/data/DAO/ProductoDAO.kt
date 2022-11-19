package com.example.amazing2.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.amazing2.data.Producto


@Dao
interface ProductoDAO {
    // Hacemos una consulta para obtener todos los productos
    @Query("SELECT * FROM Producto")
    fun getAll(): List<Producto>

    // Hacemos una consulta para obtener un producto por su id
    @Query("SELECT * FROM Producto WHERE id = :id")
    fun getById(id: Int): Producto

    // Hacemos una consulta para obtener un producto por su nombre
    @Query("SELECT * FROM Producto WHERE nombre = :nombre")
    fun getByNombre(nombre: String): Producto

    // Hacemos una consulta para obtener un producto por su idCategoria
    @Query("SELECT * FROM Producto WHERE idCategoria = :idCategoria")
    fun getByCategoria(idCategoria: Int): List<Producto>

    // Hacemos una funcion para insertar un producto
    @Insert
    fun insert(producto: Producto)

    // Hacemos una funcion para actualizar un producto
    @Update
    fun update(producto: Producto)

    // Hacemos una funcion para eliminar un producto
    @Delete
    fun delete(producto: Producto)


}



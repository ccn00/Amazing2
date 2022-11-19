package com.example.amazing2.data.DAO

import androidx.room.*
import com.example.amazing2.data.Cliente

@Dao
interface ClienteDAO {
    // Hacemos una consulta para obtener todos los clientes
    @Query("SELECT * FROM Cliente")
    fun getAll(): List<Cliente>

    // Hacemos una consulta para obtener un cliente por su id
    @Query("SELECT * FROM Cliente WHERE id = :id")
    fun getById(id: Int): Cliente

    // Hacemos una consulta para obtener un cliente por su email
    @Query("SELECT * FROM Cliente WHERE email = :email")
    fun getByEmail(email: String): Cliente

    // Hacemos una consulta para obtener un cliente por su email y password
    @Query("SELECT * FROM Cliente WHERE email = :email AND password = :password")
    fun getByEmailPassword(email: String, password: String): Cliente

    // Hacemos una consulta para insertar un cliente
    @Insert
    fun insert(cliente: Cliente)

    // Hacemos una consulta para actualizar un cliente
    @Update
    fun update(cliente: Cliente)

    // Hacemos una consulta para eliminar un cliente
    @Delete
    fun delete(cliente: Cliente)


}
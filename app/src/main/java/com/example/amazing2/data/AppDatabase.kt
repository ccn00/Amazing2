package com.example.amazing2.data

import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.amazing2.data.DAO.*

@Database(entities = [Categoria::class, Cliente::class, Compra::class, LineaCompra::class, Producto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clienteDAO(): ClienteDAO
    abstract fun productoDAO(): ProductoDAO
    abstract fun categoriaDAO(): CategoriaDAO
    abstract fun lineaCompraDAO(): LineaCompraDAO
    abstract fun compraDAO(): CompraDAO


    companion object {
        const val DATABASE_NAME = "amazing.db"
    }


}

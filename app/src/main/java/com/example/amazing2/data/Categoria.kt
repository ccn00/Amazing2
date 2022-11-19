package com.example.amazing2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Clase que representa las categorías de los productos
@Entity(tableName = "Categoria")
data class Categoria(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String
)

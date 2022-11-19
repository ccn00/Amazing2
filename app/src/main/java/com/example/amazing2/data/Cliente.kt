package com.example.amazing2.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cliente")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val apellido: String,
    val email: String,
    val password: String,
    val telefono: String,
    val direccion: String,
    val ciudad: String
)

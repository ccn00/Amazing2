package com.example.amazing2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Compra",
    foreignKeys = arrayOf(
        androidx.room.ForeignKey(
            entity = Cliente::class,
            // En cliente, el id es la clave primaria
            parentColumns = arrayOf("id"),
            // En compra, la clave foránea es idCliente
            childColumns = arrayOf("idCliente"),
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    )
)
data class Compra(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idCliente: Int,
    val fechaCompra: String,
    val precioTotal: Double
)

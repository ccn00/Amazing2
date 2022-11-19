package com.example.amazing2.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Al tener una relacion muchos a muchos, necesitamos una tabla intermedia
// Esta tabla intermedia se llama LineaCompra
// Para que no de problemas

@Entity(
    tableName = "LineaCompra",
    foreignKeys = [
        ForeignKey(
            entity = Compra::class,
            parentColumns = ["id"],
            childColumns = ["idCompra"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Producto::class,
            parentColumns = ["id"],
            childColumns = ["idProducto"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LineaCompra(
    @PrimaryKey(autoGenerate = true)
    val idLineaCompra: Int,
    // Id de la compra y Id del producto son las claves foráneas de la tabla LineaCompra
    val idCompra: Int,
    val idProducto: Int,
    val cantidad: Int
)

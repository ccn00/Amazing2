package com.example.amazing2.data

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//

@Entity(tableName = "Producto",
    foreignKeys = arrayOf(
        androidx.room.ForeignKey(
            entity = Categoria::class,
            // En categoría, el id es la clave primaria
            parentColumns = arrayOf("id"),
            // En producto, la clave foránea es idCategoria
            childColumns = arrayOf("idCategoria"),
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    )
)
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val stock : Int,
    // Para hacer una variable de una imagen

    // Clave foranea de la tabla Categoria
    val idCategoria: Int
){
    @Ignore val imagen : Bitmap? = null
}

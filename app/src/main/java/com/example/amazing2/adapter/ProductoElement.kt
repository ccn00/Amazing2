package com.example.amazing2.adapter

import android.graphics.drawable.Drawable

// Creamos una lista de productos de forma provisional con el mismo formato que la base de datos
// Este element tendra un nombre, un precio y una imagen

class ProductoElement{
    var nombre: String
    var precio: Double
    // Una variable imagen a la que se le asignara un drawable
    var imagen: Int


    // Creamos el constructor el cual recibe el nombre, el precio y la imagen
    constructor(nombre: String, precio: Double, imagen: Int) {
        this.nombre = nombre
        this.precio = precio
        this.imagen = imagen
    }


}
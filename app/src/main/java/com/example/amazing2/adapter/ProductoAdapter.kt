package com.example.amazing2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
//import com.example.amazing2.data.Producto
import com.example.amazing2.databinding.ProductElementBinding

// De forma provisional, en vez de acceder a la base de datos vamos a acceder a un array de productos ubicado en Producto_Element.kt
class ProductoAdapter () : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {
    private var productos: List<ProductoElement> = ArrayList()


    // Creamos un constructor que recibe una lista de productos
    constructor(productos: List<ProductoElement>) : this() {
        this.productos = productos
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    fun setProductos(productos: List<ProductoElement>) {
        this.productos = productos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productos[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProductElementBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(producto: ProductoElement) {
            binding.textViewNombreProducto.text = producto.nombre
            binding.textViewPrecioProducto.text = producto.precio.toString() + "€"
            binding.imageViewProducto.setImageResource(producto.imagen)
        }

    }

}








/*
//Clase adaptador de productos usando base de datos

class ProductoAdapter () : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {
    // Creamos una lista de productos
    private var productos: List<Producto> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Creamos la vista con el contexto del padre, el layout del item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productos[position])
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProductos(productos: List<Producto>) {
        this.productos = productos
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProductElementBinding.bind(itemView)

        // Función para rellenar los datos de cada elemento del recycler view
        fun bind(producto: Producto) {
            // Para acceder al elemento del recycler view, usamos itemView
            binding.textViewNombreProducto.text = producto.nombre
            binding.textViewPrecioProducto.text = producto.precio.toString()
            binding.imageViewProducto.setImageBitmap(producto.imagen)
        }
    }
}

private fun AppCompatImageView.setImageBitmap(imagen: Drawable) {
    // Funcion para asignar una imagen a un ImageView
    this.setImageDrawable(imagen)
}

*/


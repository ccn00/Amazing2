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


    fun updateList(productos: List<ProductoElement>) {
        this.productos = productos
        notifyDataSetChanged()
    }


    // Funcion Filter que recibe un texto y devuelve una lista de productos
    fun filter(text: String): List<ProductoElement> {
        // Creamos una lista de productos vacia
        val filteredList = ArrayList<ProductoElement>()
        // Recorremos la lista de productos
        for (item in productos) {
            // Si el nombre del producto contiene el texto que hemos introducido, lo añadimos a la lista
            if (item.nombre_string.lowercase().contains(text.lowercase())) {
                filteredList.add(item)
            }
        }
        // Devolvemos la lista de productos
        return filteredList
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
            binding.textViewNombreProducto.setText(producto.nombre)
            binding.textViewPrecioProducto.text = producto.precio.toString() + "€"
            binding.imageViewProducto.setImageResource(producto.imagen)

        }

    }

}
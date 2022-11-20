package com.example.amazing2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
import com.example.amazing2.adapter.ProductoAdapter
import com.example.amazing2.adapter.ProductoElement
import com.example.amazing2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    /////////////////////////////////////////////////////////////
    /*                     VARIABLES PROPIAS                   */

    var dataProducto = mutableListOf<ProductoElement>()
    private lateinit var productoAdapter : ProductoAdapter
    //private lateinit var recyclerViewProductos: RecyclerView
    // Hacemos una variable productos que es un ArrayList de ProductoElement
    var productos = ArrayList<ProductoElement>()
    var informacionCargada = false

    /////////////////////////////////////////////////////////////
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // _binding sirve para que no se produzca un memory leak
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Cargamos los elementos del xml, SEARCH y RECYCLERVIEW
        //val barraBusqueda = binding.searchView
        val recyclerViewProductos = binding.recyclerViewHome

        // Comprobamos si la informacion ha sido cargada
        if (!informacionCargada) {
            // Cargamos la informacion
            loadProductos()
            informacionCargada = true
        }

        // SHOW DATA
        recyclerViewProductos.layoutManager = LinearLayoutManager(context)
        productoAdapter = ProductoAdapter()

        // Cargamos los datos en el adapter
        productoAdapter.setProductos(productos)

        //
        recyclerViewProductos.adapter = productoAdapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Funcion que carga los productos
    fun loadProductos(){
        // Creamos un objeto de la clase ProductoElement
        // Nombre, Precio, Imagen (La imagen la cogemos de la carpeta drawable)
        // En los productos tenemos monitores, smartphones, auriculares, ratones y portatiles
        productos.add(ProductoElement("Monitor 1", 105.99, R.drawable.monitor))
        productos.add(ProductoElement("Smartphone 1", 445.99, R.drawable.movil))
        productos.add(ProductoElement("Auriculares 1", 25.99, R.drawable.auriculares))
        productos.add(ProductoElement("Ratón 1", 15.99, R.drawable.raton))
        productos.add(ProductoElement("Portatil 1", 1055.99, R.drawable.portatil))
        productos.add(ProductoElement("Monitor 2", 105.99, R.drawable.monitor))
        productos.add(ProductoElement("Smartphone 2", 445.99, R.drawable.movil))
        productos.add(ProductoElement("Auriculares 2", 25.99, R.drawable.auriculares))
        productos.add(ProductoElement("Ratón 2", 15.99, R.drawable.raton))
        productos.add(ProductoElement("Portatil 2", 1055.99, R.drawable.portatil))
        productos.add(ProductoElement("Monitor 3", 105.99, R.drawable.monitor))
        productos.add(ProductoElement("Smartphone 3", 445.99, R.drawable.movil))
        productos.add(ProductoElement("Auriculares 3", 25.99, R.drawable.auriculares))
    }
/*
    fun showProductos(){
        // Le asignamos LayoutManager al RecyclerView
        recyclerViewProductos.layoutManager = LinearLayoutManager(context)
        // Cogemos el adaptador ProductoAdapter
        productoAdapter = ProductoAdapter()
    }
    */


}
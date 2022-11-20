package com.example.amazing2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
import com.example.amazing2.adapter.ProductoAdapter
import com.example.amazing2.adapter.ProductoElement
import com.example.amazing2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    // Hacemos una variable productos que es un ArrayList de ProductoElement
    var productos = ArrayList<ProductoElement>()
    var informacionCargada = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        */

        // Declaramos la barra de busqueda y la asociamos al ViewModel
        val searchView: androidx.appcompat.widget.SearchView = binding.searchView
        homeViewModel.search.observe(viewLifecycleOwner) {
            searchView.setQuery(it, false)
        }

       ////////////////////////////////////////////////////
       /*               Recycler View                   */


        // Comprobamos si la informacion ya ha sido cargada
        if (!informacionCargada) {
            // Cargamos la informacion
            loadProductos()
            informacionCargada = true
        }

        val productoAdapter = ProductoAdapter()
        productoAdapter.setProductos(productos)

        // Declaramos el recycler view y lo asociamos al ViewModel
        val recyclerView : RecyclerView = binding.recyclerViewHome
        homeViewModel.recycler.observe(viewLifecycleOwner) {
            recyclerView.adapter = productoAdapter
        }



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
        productos.add(ProductoElement(R.string.monitor_1, 105.99, R.drawable.monitor))
        productos.add(ProductoElement(R.string.movil_1, 445.99, R.drawable.movil))
        productos.add(ProductoElement(R.string.auriculares_1, 25.99, R.drawable.auriculares))
        productos.add(ProductoElement(R.string.raton_1, 15.99, R.drawable.raton))
        productos.add(ProductoElement(R.string.portatil_1, 1055.99, R.drawable.portatil))
        productos.add(ProductoElement(R.string.monitor_2, 205.99, R.drawable.monitor))
        productos.add(ProductoElement(R.string.movil_2, 545.99, R.drawable.movil))
        productos.add(ProductoElement(R.string.auriculares_2, 35.99, R.drawable.auriculares))
        productos.add(ProductoElement(R.string.raton_2, 25.99, R.drawable.raton))
        productos.add(ProductoElement(R.string.portatil_2, 1155.99, R.drawable.portatil))
        productos.add(ProductoElement(R.string.monitor_3, 305.99, R.drawable.monitor))
        productos.add(ProductoElement(R.string.movil_3, 645.99, R.drawable.movil))
        productos.add(ProductoElement(R.string.auriculares_3, 45.99, R.drawable.auriculares))
        productos.add(ProductoElement(R.string.raton_3, 35.99, R.drawable.raton))
        productos.add(ProductoElement(R.string.portatil_3, 1255.99, R.drawable.portatil))
    }


}
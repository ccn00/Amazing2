package com.example.amazing2.ui.home

import android.app.appsearch.AppSearchResult.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
import com.example.amazing2.adapter.ProductoAdapter
import com.example.amazing2.adapter.ProductoElement
import com.example.amazing2.databinding.FragmentHomeBinding
import java.util.Locale.filter
import java.util.concurrent.RecursiveAction

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    val productoAdapter = ProductoAdapter()

    var productosFiltrados = ArrayList<ProductoElement>()

    // Hacemos una variable productos que es un ArrayList de ProductoElement
    var productos = ArrayList<ProductoElement>()

    // Hacemos una variable del searchView
    var searchView: SearchView? = null

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

        // Obtenemos el searchView



        /*
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        */

       ////////////////////////////////////////////////////
       /*               Recycler View                   */


        // Comprobamos si la informacion ya ha sido cargada
        if (!informacionCargada) {
            // Cargamos la informacion
            loadProductos()
            informacionCargada = true
        }


        productoAdapter.setProductos(productos)

        // Declaramos el recycler view y lo asociamos al ViewModel
        val recyclerView : RecyclerView = binding.recyclerViewHome
        homeViewModel.recycler.observe(viewLifecycleOwner) {
            recyclerView.adapter = productoAdapter
        }


        // Tenemos el searchView en el ViewModel

        searchView = binding.searchView

        searchView?.setOnClickListener{
            // Cuando se haga click en el searchView se ejecutara el codigo que hay dentro
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hola, ¿Que producto buscas?")
            try {
                startActivityForResult(intent, 10)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }



        // Creamos un listener para el searchView
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Manejamos la busqueda del usuario
                if (query != null) {
                    // Aplicamos el filtro a la lista de productos y actualizamos el RecyclerView

                    //productoAdapter.setProductos(productos.filter { it.nombre_string.lowercase().contains(query.lowercase()) })
                    productosFiltrados = productos.filter { it.nombre_string.lowercase().contains(query.lowercase()) } as ArrayList<ProductoElement>
                    //productosFiltrados = productoAdapter.filter(query) as ArrayList<ProductoElement>
                    productoAdapter.updateList(productosFiltrados)

                } else {
                    // Si el usuario no ha introducido nada, mostramos todos los productos
                    productoAdapter.setProductos(productos)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Manejamos la busqueda del usuario
                if (newText != null) {
                    // Aplicamos el filtro a la lista de productos y actualizamos el RecyclerView
                    /*
                    productosFiltrados = productos.filter { it.nombre_string.lowercase().contains(newText.lowercase()) } as ArrayList<ProductoElement>
                    productoAdapter.setProductos(productosFiltrados)
                    */

                    //productosFiltrados = productoAdapter.filter(newText) as ArrayList<ProductoElement>
                    productosFiltrados = productos.filter { it.nombre_string.lowercase().contains(newText.lowercase()) } as ArrayList<ProductoElement>
                    productoAdapter.updateList(productosFiltrados)

                } else {
                    // Si el usuario borra el texto, mostramos todos los productos
                    productoAdapter.setProductos(productos)
                }
                return true
            }
        })





        // Cuando el usuario introduzca texto en el searchView, se actualizara el valor del searchView en el ViewModel
        // y se actualizaran los productos mostrados en el recycler view


        /*
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Actualizamos el valor del searchView en el ViewModel, como es de tipo MutableLiveData, se actualizara automaticamente


                //productoAdapter.setProductos(productos.filter { it.nombre.contains(newText!!, true) })
                return true


            }
        })

*/



        return root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            10 -> if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                searchView?.setQuery(result?.get(0), true)
            }
        }
    }




/*
    fun updateSearch(newText: String?) {

        // Actualizamos el valor del searchView en el ViewModel
        //homeViewModel.sea = newText
    }

*/
        /*
        // Creamos un ArrayList de ProductoElement
        var productosFiltrados = ArrayList<ProductoElement>()
        // Recorremos el ArrayList de productos
        for (producto in productos) {
            // Si el nombre del producto contiene el texto introducido por el usuario, lo añadimos al ArrayList de productos filtrados

            // Como producto tiene el id del string ubicado en strings.xml, lo convertimos a string
            if (getString(producto.nombre).contains(newText.toString(), true)) {
                productosFiltrados.add(producto)
            }
        }

        // Si la lista devuelta esta vacia mandamos un mensaje de que no hay productos
        if (productosFiltrados.isEmpty()) {
            // Mostramos un mensaje de que no hay productos
            Toast.makeText(context, "No hay productos", Toast.LENGTH_SHORT).show()
        }else{
            // Si no esta vacia, actualizamos el recycler view con los productos filtrados
            productoAdapter.setProductos(productosFiltrados)
        }


    }


         */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Funcion que carga los productos
    fun loadProductos(){
        // Creamos un objeto de la clase ProductoElement
        // Nombre, Precio, Imagen (La imagen la cogemos de la carpeta drawable)
        // En los productos tenemos monitores, smartphones, auriculares, ratones y portatiles
        /*
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
         */



        productos.add(ProductoElement(R.string.monitor_1, 105.99, R.drawable.monitor, "Monitor 1"))
        productos.add(ProductoElement(R.string.auriculares_1, 25.99, R.drawable.auriculares, "Auriculares 1"))
        productos.add(ProductoElement(R.string.raton_1, 15.99, R.drawable.raton, "Raton 1"))
        productos.add(ProductoElement(R.string.portatil_1, 1055.99, R.drawable.portatil, "Portatil 1"))
        productos.add(ProductoElement(R.string.monitor_2, 205.99, R.drawable.monitor, "Monitor 2"))
        productos.add(ProductoElement(R.string.auriculares_2, 35.99, R.drawable.auriculares, "Auriculares 2"))
        productos.add(ProductoElement(R.string.raton_2, 25.99, R.drawable.raton, "Raton 2"))
        productos.add(ProductoElement(R.string.portatil_2, 1155.99, R.drawable.portatil, "Portatil 2"))
        productos.add(ProductoElement(R.string.monitor_3, 305.99, R.drawable.monitor, "Monitor 3"))
        productos.add(ProductoElement(R.string.auriculares_3, 45.99, R.drawable.auriculares, "Auriculares 3"))
        productos.add(ProductoElement(R.string.raton_3, 35.99, R.drawable.raton, "Raton 3"))
        productos.add(ProductoElement(R.string.portatil_3, 1255.99, R.drawable.portatil, "Portatil 3"))





    }


}
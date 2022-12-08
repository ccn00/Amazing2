package com.example.amazing2.ui.home

import android.app.appsearch.AppSearchResult.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
import com.example.amazing2.adapter.ProductoAdapter
import com.example.amazing2.adapter.ProductoElement
import com.example.amazing2.databinding.FragmentHomeBinding
import java.util.*
import java.util.Locale.filter
import java.util.concurrent.RecursiveAction
import kotlin.collections.ArrayList

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

        // Queremos añadirle la funcion de reconocimiento de voz al searchView para que el usuario pueda buscar por voz

        // Vamos a tener un toggle button que nos permita activar o desactivar el reconocimiento de voz


        // Añadimos el listener para el reconocimiento de voz
        searchView?.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // Cuando el usuario pulse el searchView, queremos que se abra el reconocimiento de voz
                // Para ello, creamos un intent, estamos en el fragmento HomeFragment, por lo que tenemos que usar requireContext()
                // requireContext() es el contexto de la actividad que contiene el fragmento (en este caso, MainActivity)
                // El intent es de reconocimiento de voz
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "¿Qué estás buscando?")
                // Como startActivityForResult es una funcion que esta deprecated, usamos registerForActivityResult

                try{
                    // Para iniciar la actividad dentro del fragmento, usamos requireActivity()
                    // requireActivity() es la actividad que contiene el fragmento (en este caso, MainActivity)
                    // Llamamos a la funcion registerForActivityResult, le pasamos el intent y el callback
                    // El callback es una funcion que se ejecuta cuando la actividad se ha cerrado
                    // En este caso, la actividad es el reconocimiento de voz
                    // El callback recibe un resultado, que es el resultado de la actividad
                    // El resultado es un intent, que contiene los datos que nos devuelve la actividad
                    // En este caso, los datos que nos devuelve la actividad son el texto que ha reconocido
                    // El texto que ha reconocido lo guardamos en una variable
                    // El texto que ha reconocido lo ponemos en el searchView
                    /*
                    requireActivity().registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.resultCode == RESULT_OK) {
                            val data: Intent? = result.data
                            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                            searchView?.setQuery(result?.get(0), false)
                        }
                    }.launch(intent)
                    */
                    startActivityForResult(intent, 10)
                    //startActivityForResult(intent, 10)
                } catch (e: Exception) {
                    Toast.makeText(context, "Tu dispositivo no soporta el reconocimiento de voz", Toast.LENGTH_SHORT).show()
                }
            }
        }



        // Creamos un listener para el searchView
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Manejamos la busqueda del usuario
                if (query != null) {
                    // Aplicamos el filtro a la lista de productos y actualizamos el RecyclerView
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
                    productosFiltrados = productos.filter { it.nombre_string.lowercase().contains(newText.lowercase()) } as ArrayList<ProductoElement>
                    productoAdapter.updateList(productosFiltrados)

                } else {
                    // Si el usuario borra el texto, mostramos todos los productos
                    productoAdapter.setProductos(productos)
                }
                return true
            }
        })


        return root
    }


    // Hacemos una funcion que maneje el resultado del reconocimiento de voz
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            10 -> if ( data != null) {
                // Obtenemos el resultado del reconocimiento de voz
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                // Lo añadimos al searchView
                searchView?.setQuery(result?.get(0), true)
            } else {
                Toast.makeText(context, "No se ha podido reconocer la voz correctamente", Toast.LENGTH_SHORT).show()
            }
        }
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
        productos.add(ProductoElement(R.string.monitor_1, 105.99, R.drawable.monitor, "Monitor 1"))
        productos.add(ProductoElement(R.string.auriculares_1, 25.99, R.drawable.auriculares, "Auriculares 1"))
        productos.add(ProductoElement(R.string.raton_1, 15.99, R.drawable.raton, "Ratón raton 1"))
        productos.add(ProductoElement(R.string.portatil_1, 1055.99, R.drawable.portatil, "Portátil portatil 1"))
        productos.add(ProductoElement(R.string.monitor_2, 205.99, R.drawable.monitor, "Monitor 2"))
        productos.add(ProductoElement(R.string.auriculares_2, 35.99, R.drawable.auriculares, "Auriculares 2"))
        productos.add(ProductoElement(R.string.raton_2, 25.99, R.drawable.raton, "Ratón raton 2"))
        productos.add(ProductoElement(R.string.portatil_2, 1155.99, R.drawable.portatil, "Portatil portátil 2"))
        productos.add(ProductoElement(R.string.monitor_3, 305.99, R.drawable.monitor, "Monitor 3"))
        productos.add(ProductoElement(R.string.auriculares_3, 45.99, R.drawable.auriculares, "Auriculares 3"))
        productos.add(ProductoElement(R.string.raton_3, 35.99, R.drawable.raton, "Ratón Raton 3"))
        productos.add(ProductoElement(R.string.portatil_3, 1255.99, R.drawable.portatil, "Portatil portátil 3"))

    }


}
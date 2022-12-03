package com.example.amazing2.ui.perfil

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.amazing2.R
import com.example.amazing2.adapter.PerfilElement
import com.example.amazing2.adapter.UtilidadesAdapter
import com.example.amazing2.adapter.UtilidadesElement
import com.example.amazing2.databinding.FragmentUtilidadesBinding




class Utilidades : Fragment(), UtilidadesAdapter.OnItemClickListener {

    private var _binding: FragmentUtilidadesBinding? = null


    private val binding get() = _binding!!

    var utilidadesElement = ArrayList<UtilidadesElement>()
    var informacionCargada = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Quitamos la barra de navegacion
        requireActivity().findViewById<View>(R.id.nav_view).visibility = View.GONE

    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val utilidadesViewModel =
            ViewModelProvider(this).get(ViewModelUtilidades::class.java)


        _binding = FragmentUtilidadesBinding.inflate(inflater, container, false)
        val root: View = binding.root



        // Comprobamos si la informacion ya ha sido cargada
        if (!informacionCargada) {
            // Cargamos la informacion
            loadUtilidadesElements()
            informacionCargada = true
        }

        val utilidadesAdapter = UtilidadesAdapter(this)
        utilidadesAdapter.setUtilidadesElements(utilidadesElement)


        val recyclerView: RecyclerView = binding.recyclerViewUtilidades
        utilidadesViewModel.recycler.observe(viewLifecycleOwner) {
            recyclerView.adapter = utilidadesAdapter
        }


        return root
    }



    companion object {}

    // Hay un boton de atras en el toolbar que nos lleva a la pantalla anterior (perfil)
    // Hay que sobreescribir el metodo onOptionsItemSelected para que funcione

    override fun onItemClick(position: Int) {
        when(position){
            0 -> {
                // Codigo de barras
                // Mostramos en un toast el nombre del elemento
                Toast.makeText(context, "Codigo de barras", Toast.LENGTH_SHORT).show()
            }
            1 -> {
                // NFC
                Toast.makeText(context, "NFC", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Eliminamos la referencia al binding para evitar fugas de memoria
        _binding = null

        // Mostramos la barra de navegacion
        requireActivity().findViewById<View>(R.id.nav_view).visibility = View.VISIBLE
    }

    private fun loadUtilidadesElements(){
        // Estructura de perfilElement
        utilidadesElement.add(UtilidadesElement(R.string.codigo_barras, R.drawable.ic_baseline_qr_code_scanner_24))
        utilidadesElement.add(UtilidadesElement(R.string.nfc, R.drawable.ic_baseline_nfc_24))

    }
}

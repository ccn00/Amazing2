package com.example.amazing2.ui.perfil

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.amazing2.R
import com.example.amazing2.adapter.PerfilElement
import com.example.amazing2.adapter.UtilidadesAdapter
import com.example.amazing2.adapter.UtilidadesElement
import com.example.amazing2.databinding.FragmentUtilidadesBinding
import com.google.zxing.integration.android.IntentIntegrator


class Utilidades : Fragment(), UtilidadesAdapter.OnItemClickListener {

    private var _binding: FragmentUtilidadesBinding? = null


    private val binding get() = _binding!!

    var utilidadesElement = ArrayList<UtilidadesElement>()
    var informacionCargada = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Quitamos la barra de navegacion
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

        requireActivity().findViewById<View>(R.id.nav_view).visibility = View.GONE

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
                //Toast.makeText(context, "Codigo de barras", Toast.LENGTH_SHORT).show()

                // Lanzamos la actividad de codigo de barras sin el initiateScan porque esta Deprecated
                // Debemos asignar a zxingActivityResultLauncher el resultado de la actividad
                initScanner()

            }
            1 -> {
                // NFC
                Toast.makeText(context, "NFC", Toast.LENGTH_SHORT).show()
            }
            2 -> {
                // Mapa
                Toast.makeText(context, "Mapa", Toast.LENGTH_SHORT).show()
                // Cambiamos al fragmento del mapa
                view?.let { Navigation.findNavController(it).navigate(R.id.action_navigation_utilidades_to_mapsFragment) }
            }
        }
    }

    private fun initScanner() {
        /*
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Escanear")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
         */

        /*
        private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
        result -> {
            if(result.getContents() == null) {
                Toast.makeText(MyActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MyActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        });

         */
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Escanear")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()


    }


    // Lógica codigo de Barras

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(context, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                when(result.contents){
                    "6700828501046812039315;724001;2022-11-16T18:02:10.453+01:00;e6ed0d40eba4494d9a9b78416381bd2f;;" -> {
                        Toast.makeText(context, "Ticket", Toast.LENGTH_LONG).show()
                    }
                    "987654321" -> {
                        Toast.makeText(context, "Producto 2", Toast.LENGTH_LONG).show()
                    }
                    "5000394203969" -> {
                        Toast.makeText(context, "Monitor", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Toast.makeText(context, "Producto no encontrado", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
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
        utilidadesElement.add(UtilidadesElement(R.string.ruta_mas_cercana, R.drawable.ic_baseline_map_24))

    }
}

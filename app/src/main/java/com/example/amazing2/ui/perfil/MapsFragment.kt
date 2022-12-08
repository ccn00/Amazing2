package com.example.amazing2.ui.perfil

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.example.amazing2.MainActivity
import com.example.amazing2.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {


    private lateinit var fusedLocationClient: FusedLocationProviderClient

    var idBtn : Int = 0

    /*
        private val viewModel by lazy {
            ViewModelProvider(this).get(ViewModelUtilidades::class.java)
        }
    */
    private lateinit var lastLocation: LatLng
    // Feria
    val tienda1 = LatLng(38.9972549, -1.86999)
    // Campus
    val tienda2 = LatLng(38.9789743, -1.85265)
    // Estacion
    val tienda3 = LatLng(39.0001183, -1.8487)

    val albacete = LatLng(38.994, -1.858)

    private lateinit var tiendaMasCercana : String

    private val callback = OnMapReadyCallback { googleMap ->
        // Comprobamos la variable del viewModel de utilidades, si es 2 es que se ha pulsado el boton de la tienda mas cercana

        // Añadimos marcador en posicion actual con la forma de un circulo azul
        googleMap.addMarker(MarkerOptions().position(lastLocation).title("Posicion actual").icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker(com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_AZURE)))


        if (idBtn == 2) {
            calcularTiendaMasCercana()

            when (tiendaMasCercana) {
                "Feria" -> {
                    googleMap.addMarker(MarkerOptions().position(tienda1).title("Tienda de la Feria"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tienda1, 15f))
                }
                "Campus" -> {
                    googleMap.addMarker(MarkerOptions().position(tienda2).title("Tienda de Campus"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tienda2, 15f))
                }
                "Estacion" -> {
                    googleMap.addMarker(MarkerOptions().position(tienda3).title("Tienda de la Estacion"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tienda3, 15f))
                }
                else -> {
                    googleMap.addMarker(MarkerOptions().position(albacete).title("Albacete"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(albacete, 15f))
                }
            }
        } else {
            // Mostramos las 3 tiendas

            googleMap.addMarker(MarkerOptions().position(tienda1).title("Tienda de la Feria"))
            googleMap.addMarker(MarkerOptions().position(tienda2).title("Tienda Campus"))
            googleMap.addMarker(MarkerOptions().position(tienda3).title("Tienda de la Estacion"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(albacete, 14f))

        }
    }


    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_maps, container, false)



        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        // Obtenemos la ubicacion actual del usuario

        lastLocation = LatLng(location!!.latitude, location.longitude)



        // Para obtener los datos pasados por el bundle
        val bundle = this.arguments
        if (bundle != null) {
            idBtn = bundle.getInt("idBoton")
        }


        requireActivity().findViewById<View>(R.id.nav_view).visibility = View.GONE

        return root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


    // Creamos una funcion para calcular la tienda mas cercana al usuario
    fun calcularTiendaMasCercana() {
        // Para calcular la tienda mas cercana al usuario, debemos calcular la distancia entre el usuario y cada una de las tiendas
        // Para calcular la distancia entre dos puntos, usaremos la distancia euclidea

        //lastLocation = LatLng(38.97883, -1.85590)
        // Calculamos la distancia entre el usuario y la tienda 1
        val distancia1 = Math.sqrt(Math.pow(lastLocation.latitude - tienda1.latitude, 2.0) + Math.pow(lastLocation.longitude - tienda1.longitude, 2.0))

        // Calculamos la distancia entre el usuario y la tienda 2
        val distancia2 = Math.sqrt(Math.pow(lastLocation.latitude - tienda2.latitude, 2.0) + Math.pow(lastLocation.longitude - tienda2.longitude, 2.0))

        // Calculamos la distancia entre el usuario y la tienda 3
        val distancia3 = Math.sqrt(Math.pow(lastLocation.latitude - tienda3.latitude, 2.0) + Math.pow(lastLocation.longitude - tienda3.longitude, 2.0))

        // Calculamos la distancia minima
        val distanciaMinima = Math.min(distancia1, Math.min(distancia2, distancia3))

        // Calculamos la tienda mas cercana
        tiendaMasCercana = if (distanciaMinima == distancia1) {
            "Feria"
        } else if (distanciaMinima == distancia2) {
            "Campus"
        } else {
            "Estacion"
        }
    }

}
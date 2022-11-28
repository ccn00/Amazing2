package com.example.amazing2.ui.perfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amazing2.R
import com.example.amazing2.adapter.PerfilElement


class Utilidades : Fragment() {

    var utilidadesElement = ArrayList<PerfilElement>()
    var informacionCargada = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        // Comprobamos si la informacion ya ha sido cargada
        if (!informacionCargada) {
            // Cargamos la informacion
            loadUtilidadesElements()
            informacionCargada = true
        }

        return inflater.inflate(R.layout.fragment_utilidades, container, false)
    }

    companion object {}


    fun loadUtilidadesElements(){
        // Estructura de perfilElement
        utilidadesElement.add(PerfilElement(R.string.codigo_barras, R.drawable.ic_baseline_qr_code_scanner_24))
        utilidadesElement.add(PerfilElement(R.string.nfc, R.drawable.ic_baseline_nfc_24))

    }
}

package com.example.amazing2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
import com.example.amazing2.adapter.PerfilElement
import com.example.amazing2.adapter.PerfilElementAdapter
import com.example.amazing2.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var profileElements = ArrayList<PerfilElement>()
    var informacionCargada = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val perfilViewModel =
            ViewModelProvider(this).get(PerfilViewModel::class.java)

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Comprobamos si la informacion ya ha sido cargada
        if (!informacionCargada) {
            // Cargamos la informacion
            loadProfileElements()
            informacionCargada = true
        }

        val perfilAdapter = PerfilElementAdapter()
        perfilAdapter.setPerfilElements(profileElements)

        val recyclerView: RecyclerView = binding.recyclerViewPerfil
        perfilViewModel.recycler.observe(viewLifecycleOwner) {
            recyclerView.adapter = perfilAdapter
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun loadProfileElements(){
        // Estructura de perfilElement
        profileElements.add(PerfilElement(R.string.mis_pedidos, R.drawable.ic_baseline_shopping_basket_24))
        profileElements.add(PerfilElement(R.string.informacion_entrega, R.drawable.ic_baseline_info_24))
        profileElements.add(PerfilElement(R.string.productos_guardados, R.drawable.ic_baseline_save_24))
        profileElements.add(PerfilElement(R.string.visto_recientemente, R.drawable.ic_baseline_remove_red_eye_24))
        profileElements.add(PerfilElement(R.string.historial_compras, R.drawable.ic_baseline_history_24))
        profileElements.add(PerfilElement(R.string.ajustes, R.drawable.ic_baseline_settings_24))

    }
}
package com.example.amazing2.ui.perfil

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
import com.example.amazing2.adapter.PerfilElement
import com.example.amazing2.adapter.PerfilElementAdapter
import com.example.amazing2.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment(), PerfilElementAdapter.OnItemClickListener {

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

        val perfilAdapter = PerfilElementAdapter(this)
        perfilAdapter.setPerfilElements(profileElements)

        val recyclerView: RecyclerView = binding.recyclerViewPerfil
        perfilViewModel.recycler.observe(viewLifecycleOwner) {
            recyclerView.adapter = perfilAdapter
        }


        return root
    }



    override fun onItemClick(position: Int) {
        //val clickedItem: PerfilElement = profileElements[position]

        // Si se ha pulsado el item 5 ajustes, abrimos la actividad de ajustes de accesibilidad
        when (position) {
            4 -> {
                Toast.makeText(context, "Utilidades", Toast.LENGTH_SHORT).show()

                // Cambiamos de vista con Navigation
                //val navController: NavController = Navigation.findNavController(requireActivity(), R.id.)
                view?.let { Navigation.findNavController(it).navigate(R.id.action_navigation_perfil_to_navigation_utilidades) }

            }

            5 -> {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
            else -> {
                Toast.makeText(context, "Elemento $position", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun loadProfileElements(){
        // Estructura de perfilElement
        profileElements.add(PerfilElement(R.string.mis_pedidos, R.drawable.ic_baseline_shopping_basket_24))
        profileElements.add(PerfilElement(R.string.informacion_entrega, R.drawable.ic_baseline_info_24))
        profileElements.add(PerfilElement(R.string.productos_guardados, R.drawable.ic_baseline_save_24))
        profileElements.add(PerfilElement(R.string.visto_recientemente, R.drawable.ic_baseline_remove_red_eye_24))
        profileElements.add(PerfilElement(R.string.utilidades, R.drawable.ic_baseline_build_24))
        profileElements.add(PerfilElement(R.string.ajustes, R.drawable.ic_baseline_settings_24))

    }
}
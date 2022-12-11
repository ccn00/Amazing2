package com.example.amazing2.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amazing2.MainActivity
import com.example.amazing2.R
import com.example.amazing2.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null


    lateinit var producto: String
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val bundle = this.arguments
        if (bundle != null) {
            producto = bundle.getString("producto").toString()
        }


        // Llamamos a la funcion para cargar la informacion
        loadProducto()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    fun loadProducto() {
        // Cargamos la informacion del producto
        /*
        binding.textViewDetallesNombreProducto.text = "Monitor"
        binding.textViewDetallesPrecioProducto.text = "Precio
        */

        // Cuando el producto sea "monitor"
        when (producto){
            "monitor" -> {
                binding.textViewDetallesNombreProducto.text = "Monitor"
                binding.textViewDetallesPrecioProducto.text = "Precio: 100€"
                binding.imageViewDetallesProducto.setImageResource(R.drawable.monitor)
                (activity as MainActivity).supportActionBar?.title = "Monitor"
            }
            "raton" -> {
                binding.textViewDetallesNombreProducto.text = "Raton"
                binding.textViewDetallesPrecioProducto.text = "Precio: 10€"
                binding.imageViewDetallesProducto.setImageResource(R.drawable.raton)
                (activity as MainActivity).supportActionBar?.title = "Ratón"
            }
            "auriculares" -> {
                binding.textViewDetallesNombreProducto.text = "Auriculares"
                binding.textViewDetallesPrecioProducto.text = "Precio: 20€"
                binding.imageViewDetallesProducto.setImageResource(R.drawable.auriculares)
                (activity as MainActivity).supportActionBar?.title = "Auriculares"
            }
        }


    }


}

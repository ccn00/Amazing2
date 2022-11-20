package com.example.amazing2.ui.carrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.amazing2.R
import com.example.amazing2.databinding.FragmentCarritoBinding

class CarritoFragment : Fragment() {

    private var _binding: FragmentCarritoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val carritoViewModel =
            ViewModelProvider(this).get(CarritoViewModel::class.java)

        _binding = FragmentCarritoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCarritoVacio
        carritoViewModel.text.observe(viewLifecycleOwner) {
            textView.setText(R.string.carrito_vacio).toString()
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
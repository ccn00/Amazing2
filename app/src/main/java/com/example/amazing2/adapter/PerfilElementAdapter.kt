package com.example.amazing2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
import com.example.amazing2.databinding.ProfileElementBinding


class PerfilElementAdapter : RecyclerView.Adapter<PerfilElementAdapter.ViewHolder>() {

    private var perfilElements: List<PerfilElement> = ArrayList()



    override fun getItemCount(): Int {
        return perfilElements.size
    }

    fun setPerfilElements(perfilElements: List<PerfilElement>) {
        this.perfilElements = perfilElements
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(perfilElements[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProfileElementBinding.bind(itemView)

        fun bind(perfilElement: PerfilElement) {
            binding.textViewNombreProfile.setText(perfilElement.nombre)
            binding.imageViewIcon.setImageResource(perfilElement.icono)

        }

    }

}

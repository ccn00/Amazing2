package com.example.amazing2.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing2.R
import com.example.amazing2.databinding.ProfileElementBinding

class UtilidadesAdapter (private val listener: OnItemClickListener) : RecyclerView.Adapter<UtilidadesAdapter.ViewHolder>() {

    private var utilidadesElements: List<UtilidadesElement> = ArrayList()

    override fun getItemCount(): Int {
        return utilidadesElements.size
    }

    fun setUtilidadesElements(utilidadesElements: List<UtilidadesElement>) {
        this.utilidadesElements = utilidadesElements
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(utilidadesElements[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val binding = ProfileElementBinding.bind(itemView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(utilidadesElement: UtilidadesElement) {
            binding.textViewNombreProfile.setText(utilidadesElement.nombre)
            binding.imageViewIcon.setImageResource(utilidadesElement.icono)

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }

        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}




/*

class UtilidadesAdapter (private val listener: OnItemClickListener) : RecyclerView.Adapter<UtilidadesAdapter.ViewHolder>() {

    private var utilidadesElements: List<UtilidadesElement> = ArrayList()



    override fun getItemCount(): Int {
        return utilidadesElements.size
    }

    fun setUtilidadesElements(utilidadesElements: List<UtilidadesElement>) {
        this.utilidadesElements = utilidadesElements
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(utilidadesElements[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val binding = ProfileElementBinding.bind(itemView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(perfilElement: UtilidadesElement) {
            binding.textViewNombreProfile.setText(perfilElement.nombre)
            binding.imageViewIcon.setImageResource(perfilElement.icono)

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }

        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}
*/
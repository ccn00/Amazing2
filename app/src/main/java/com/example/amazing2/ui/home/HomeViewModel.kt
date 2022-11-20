package com.example.amazing2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amazing2.adapter.ProductoElement

class HomeViewModel : ViewModel() {
    /*
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    */

    // Añadimos la barra de busqueda al ViewModel
    private val _search = MutableLiveData<String>().apply {
        value = "Buscador"
    }
    val search: LiveData<String> = _search

    /*
    // Añadimos el metodo para actualizar el valor de la barra de busqueda
    fun updateSearch(newSearch: String) {
        _search.value = newSearch
    }
    */

    // Añadimos el recycler view al ViewModel, este sera llenado por el adapter de productos
    private val _recycler = MutableLiveData<List<ProductoElement>>().apply {
        value = listOf()
    }
    val recycler: LiveData<List<ProductoElement>> = _recycler





}
package com.example.amazing2.ui.home

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.contentValuesOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amazing2.adapter.ProductoAdapter
import com.example.amazing2.adapter.ProductoElement
import kotlin.coroutines.coroutineContext

class HomeViewModel : ViewModel() {

    /*
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    */

    // Añadimos el SearchView al ViewModel para que se pueda acceder desde el Fragment
    // como este se va a ir cambiando, lo hacemos mutable
    private var _searchView = MutableLiveData<SearchView>()
    var searchView: LiveData<SearchView> = _searchView





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
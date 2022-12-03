package com.example.amazing2.ui.perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amazing2.adapter.UtilidadesElement

class ViewModelUtilidades: ViewModel() {

    private val _recycler = MutableLiveData<List<UtilidadesElement>>().apply {
        value = listOf()
    }
    val recycler: LiveData<List<UtilidadesElement>> = _recycler

}

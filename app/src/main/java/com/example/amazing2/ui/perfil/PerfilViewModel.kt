package com.example.amazing2.ui.perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amazing2.adapter.PerfilElement

class PerfilViewModel : ViewModel() {

    private val _recycler = MutableLiveData<List<PerfilElement>>().apply {
        value = listOf()
    }
    val recycler: LiveData<List<PerfilElement>> = _recycler



}
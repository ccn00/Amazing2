package com.example.amazing2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


// La clase ViewModel se usa para almacenar y administrar datos relacionados con la IU.
// La IU se puede vincular a datos observables almacenados en el ViewModel y se puede
// notificar a la IU cuando los datos cambian.
class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}
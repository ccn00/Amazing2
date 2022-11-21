package com.example.amazing2.ui.carrito

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amazing2.R

class CarritoViewModel : ViewModel() {

    // Queremos hacer un textView el cual sera rellenado con un string que se encuentra en el archivo strings.xml

    private val _text = MutableLiveData<String>().apply {
        value = "This is carrito Fragment"
    }
    val text: LiveData<String> = _text




}
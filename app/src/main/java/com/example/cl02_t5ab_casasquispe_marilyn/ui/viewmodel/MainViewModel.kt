package com.example.cl02_t5ab_casasquispe_marilyn.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cl02_t5ab_casasquispe_marilyn.room.Producto
import com.example.cl02_t5ab_casasquispe_marilyn.repository.ProductoRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductoRepository) : ViewModel() {
    val todosLosProductos : LiveData<List<Producto>> = repository.productosAlmacen.asLiveData()

    fun agregar(nuevo: Producto){
        viewModelScope.launch {
            repository.insertar(nuevo)
        }
    }

    fun actualizar(nuevo: Producto){
        viewModelScope.launch {
            repository.actualizar(nuevo)
        }
    }


    fun eliminar(nuevo: Producto){
        viewModelScope.launch {
            repository.eliminar(nuevo)
        }
    }
}
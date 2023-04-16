package com.example.cl02_t5ab_casasquispe_marilyn.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cl02_t5ab_casasquispe_marilyn.repository.ProductoRepository
import com.example.cl02_t5ab_casasquispe_marilyn.ui.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: ProductoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}
package com.example.cl02_t5ab_casasquispe_marilyn.app
import android.app.Application
import com.example.cl02_t5ab_casasquispe_marilyn.repository.ProductoRepository
import com.example.cl02_t5ab_casasquispe_marilyn.room.ProductoDataBase

class ProductoAplication : Application() {
    val database by lazy { ProductoDataBase.getDataBase(this) }
    val repository by lazy { ProductoRepository(database.producto())}
}
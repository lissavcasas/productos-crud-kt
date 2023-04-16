package com.example.cl02_t5ab_casasquispe_marilyn.repository
import com.example.cl02_t5ab_casasquispe_marilyn.room.ProductoDao
import com.example.cl02_t5ab_casasquispe_marilyn.room.Producto
import kotlinx.coroutines.flow.Flow

class ProductoRepository (private val productoDao: ProductoDao) {
    val productosAlmacen : Flow<List<Producto>> = productoDao.obtenerProductosOrdenados()

    suspend fun insertar(producto: Producto){
        productoDao.insertarProducto(producto)
    }

    suspend fun actualizar(producto: Producto){
        productoDao.actualizarProducto(producto)
    }

    suspend fun eliminar(producto: Producto){
        productoDao.eliminarProducto(producto)
    }
}
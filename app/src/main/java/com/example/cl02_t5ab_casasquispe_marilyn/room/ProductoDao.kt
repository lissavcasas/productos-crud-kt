package com.example.cl02_t5ab_casasquispe_marilyn.room
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Query("SELECT * from TblProducto ORDER BY nombre ASC")
    fun obtenerProductosOrdenados(): Flow<List<Producto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarProducto(producto: Producto)

    @Update
    suspend fun actualizarProducto(producto: Producto)

    @Delete
    suspend fun eliminarProducto(producto: Producto)
}
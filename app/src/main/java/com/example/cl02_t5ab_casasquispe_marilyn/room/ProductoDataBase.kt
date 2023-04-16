package com.example.cl02_t5ab_casasquispe_marilyn.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Producto::class], version = 1)
abstract class ProductoDataBase : RoomDatabase(){
    abstract fun producto(): ProductoDao

    companion object{
        @Volatile
        private var INSTANCE: ProductoDataBase? = null

        fun getDataBase(context: Context): ProductoDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductoDataBase::class.java,
                    "almacen_cueva")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
package com.example.cl02_t5ab_casasquispe_marilyn.room
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "TblProducto")
data class Producto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val identificador: Int = 0,
    @ColumnInfo(name = "codigo") val codigo: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "cantidad") val cantidad: Int,
)  : Serializable {
    override fun toString(): String {
        return "Producto(identificador=$identificador,codigo=$codigo, nombre='$nombre', cantidad='$cantidad')"
    }
}
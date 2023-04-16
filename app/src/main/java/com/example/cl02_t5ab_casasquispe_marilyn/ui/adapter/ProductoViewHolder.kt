package com.example.cl02_t5ab_casasquispe_marilyn.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.cl02_t5ab_casasquispe_marilyn.databinding.ItemProductoBinding
import com.example.cl02_t5ab_casasquispe_marilyn.room.Producto

class ProductoViewHolder (val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(producto : Producto){
        binding.lblCodigo.text =  "Código: " + producto.codigo
        binding.lblNombre.text = producto.nombre
        binding.lblCantidad.text = "Cantidad: " + producto.cantidad.toString()
    }
}


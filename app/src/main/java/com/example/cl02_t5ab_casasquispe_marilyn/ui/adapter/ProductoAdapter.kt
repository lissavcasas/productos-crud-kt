package com.example.cl02_t5ab_casasquispe_marilyn.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.cl02_t5ab_casasquispe_marilyn.databinding.ItemProductoBinding
import com.example.cl02_t5ab_casasquispe_marilyn.room.Producto

class ProductoAdapter (val lista: List<Producto>, val click : (Producto, Int) -> Unit, val clickLargo : (Producto) -> Unit) : Adapter<ProductoViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = lista[position]
        holder.bind(producto)

        holder.itemView.setOnClickListener {
            click(producto, holder.layoutPosition)
        }
        holder.itemView.setOnLongClickListener {
            clickLargo(producto)
            true
        }
    }
}
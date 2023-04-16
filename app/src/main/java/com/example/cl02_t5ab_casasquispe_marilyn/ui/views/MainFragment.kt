package com.example.cl02_t5ab_casasquispe_marilyn.ui.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cl02_t5ab_casasquispe_marilyn.R
import com.example.cl02_t5ab_casasquispe_marilyn.app.ProductoAplication
import com.example.cl02_t5ab_casasquispe_marilyn.databinding.FragmentMainBinding
import com.example.cl02_t5ab_casasquispe_marilyn.factory.ViewModelFactory
import com.example.cl02_t5ab_casasquispe_marilyn.room.Producto
import com.example.cl02_t5ab_casasquispe_marilyn.ui.adapter.ProductoAdapter
import com.example.cl02_t5ab_casasquispe_marilyn.ui.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val productoViewModel: MainViewModel by viewModels {
        ViewModelFactory((requireActivity().application as ProductoAplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        productoViewModel.todosLosProductos.observe(viewLifecycleOwner){lstProductos ->
             println("El tamaño de la lista es ${lstProductos.size}")
            binding.rvProducto.adapter = ProductoAdapter(
                lstProductos,
                { producto, posicion ->
                    println("VALOR: ${producto.nombre} en la posicion: $posicion")
                    val action = MainFragmentDirections.actionMainFragmentToDetalleFragment(producto)
                    findNavController().navigate(action)
                }
                ,
                { producto ->
                    println("CLICK LARGO : ${producto.nombre}")
                    binding.btnAdd.setOnClickListener {
                        val fragment = DetalleFragment()
                        val fragmentManager = requireActivity().supportFragmentManager
                        fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .addToBackStack(null)
                            .commit()
                    }
                }
            )

        }
        binding.rvProducto.layoutManager = GridLayoutManager(requireContext(), 1)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_agregar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_agregar -> {
                val action = MainFragmentDirections.actionMainFragmentToDetalleFragment()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
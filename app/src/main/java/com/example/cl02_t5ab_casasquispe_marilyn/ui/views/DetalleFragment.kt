package com.example.cl02_t5ab_casasquispe_marilyn.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cl02_t5ab_casasquispe_marilyn.R
import com.example.cl02_t5ab_casasquispe_marilyn.app.ProductoAplication
import com.example.cl02_t5ab_casasquispe_marilyn.databinding.FragmentDetalleBinding
import com.example.cl02_t5ab_casasquispe_marilyn.factory.ViewModelFactory
import com.example.cl02_t5ab_casasquispe_marilyn.room.Producto
import com.example.cl02_t5ab_casasquispe_marilyn.ui.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetalleFragment : Fragment() {
    private var _binding: FragmentDetalleBinding? = null
    val binding get() = _binding!!

    private val productoViewModel: MainViewModel by viewModels {
        ViewModelFactory((requireActivity().application as ProductoAplication).repository)
    }

    private val args: DetalleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productoSelected = args.producto

        if (productoSelected == null) {

            binding.btnAgregar.setOnClickListener {
                // Leer la informacion que ingresa el usuario
                val codigo = binding.txtCodigo.editText?.text.toString()
                val nombre = binding.txtNombre.editText?.text.toString()
                val cantidad = binding.txtCantidad.editText?.text.toString()


                if (codigo.isEmpty()) {
                    binding.txtNombre.error = requireActivity().resources.getString(R.string.error_campo_requerido)
                    return@setOnClickListener
                }

                if (nombre.isEmpty()) {
                    binding.txtNombre.error = requireActivity().resources.getString(R.string.error_campo_requerido)
                    return@setOnClickListener
                }

                if (cantidad.isEmpty()) {
                    binding.txtCantidad.error = requireActivity().resources.getString(R.string.error_campo_requerido)
                    return@setOnClickListener
                }

                MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_info)
                    .setTitle("Confirmación")
                    .setMessage("¿Desea registrar un nuevo producto?")
                    .setPositiveButton("Aceptar") { _, _ ->
                        //Guardar nuevo producto
                        val nuevoProducto = Producto(codigo = codigo, nombre = nombre, cantidad = Integer.parseInt(cantidad))
                        productoViewModel.agregar(nuevoProducto)
                        findNavController().popBackStack()
                    }
                    .setNegativeButton("Cancelar", null)
                    .setNeutralButton("Cerrar", null)
                    .show()
            }
            binding.btnActualizar.visibility = View.GONE
            binding.btnEliminar.visibility = View.GONE
        } else {
            binding.txtCodigo.editText?.setText(productoSelected.codigo)
            binding.txtNombre.editText?.setText(productoSelected.nombre)
            binding.txtCantidad.editText?.setText(productoSelected.cantidad.toString())

            binding.btnActualizar.setOnClickListener {
                // Leer la informacion que ingresa el usuario
                val codigo = binding.txtCodigo.editText?.text.toString()
                val nombre = binding.txtNombre.editText?.text.toString()
                val cantidad = binding.txtCantidad.editText?.text.toString()

                if (codigo.isEmpty()) {
                    binding.txtNombre.error = requireActivity().resources.getString(R.string.error_campo_requerido)
                    return@setOnClickListener
                }

                if (nombre.isEmpty()) {
                    binding.txtNombre.error = requireActivity().resources.getString(R.string.error_campo_requerido)
                    return@setOnClickListener
                }

                if (cantidad.isEmpty()) {
                    binding.txtCantidad.error = requireActivity().resources.getString(R.string.error_campo_requerido)
                    return@setOnClickListener
                }

                MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_info)
                    .setTitle("Confirmación")
                    .setMessage("¿Desea actualizar este producto?")
                    .setPositiveButton("Aceptar") { _, _ ->
                        //Codigo para guardar un nuevo producto
                        val productoActualizado = productoSelected.copy(codigo = codigo, nombre = nombre, cantidad = Integer.parseInt(cantidad))
                        productoViewModel.actualizar(productoActualizado)
                        findNavController().popBackStack()
                    }
                    .setNegativeButton("Cancelar", null)
                    .setNeutralButton("Cerrar",null)
                    .show()
            }

            binding.btnEliminar.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_warning)
                    .setTitle("Advertencia")
                    .setMessage("¿Desea eliminar este producto?")
                    .setPositiveButton("Eliminar") { _, _ ->
                        productoViewModel.eliminar(productoSelected!!)
                        findNavController().popBackStack()
                    }
                    .setNegativeButton("Cancelar", null)
                    .setNeutralButton("Cerrar", null)
                    .show()
            }
            binding.btnAgregar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
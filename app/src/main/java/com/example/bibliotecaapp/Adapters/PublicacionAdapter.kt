package com.example.bibliotecaapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecaapp.Interfaces.IOnClickListener
import com.example.bibliotecaapp.Models.LibroEntity
import com.example.bibliotecaapp.Models.Publicacion
import com.example.bibliotecaapp.Models.RevistaEntity
import com.example.bibliotecaapp.R
import com.example.bibliotecaapp.databinding.ItemPublicacionListBinding

class PublicacionAdapter(private var lstPublicaciones: MutableList<Publicacion>, private val listener: IOnClickListener)
    : RecyclerView.Adapter<PublicacionAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layout = LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.item_publicacion_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lstPublicaciones[position]
        // Uso de funcion de alcance para agregar acciones al objeto en un mismo bloque
        with(holder){
            if(item.getTipoPublicacion() == 1){
                // Libro
                // A continuación se realiza un parceo o casteo
                val libroEntity: LibroEntity = item as LibroEntity
                setListener(libroEntity, position)
                // Configurando contenido del cardview en base al objeto casteado
                binding.txvcodigo.text = libroEntity.getCode().toString()
                binding.txvTitulo.text = libroEntity.getTitle()
                binding.txvAnio.text = libroEntity.getYearPub().toString()
                binding.txvTipoPublicacion.text = context.resources.getString(R.string.libro_tag)
                // Se evalua el estado del libro
                if(libroEntity.Prestado()){
                    binding.txvEstado.text = context.resources.getString(R.string.estado_tag)
                } else {
                    binding.txvEstado.text = context.resources.getString(R.string.estado_disp_tag)
                }
            } else {
                // Revista
                val revistaEntity: RevistaEntity = item as RevistaEntity
                // Configurando contenido del cardview en base al objeto casteado
                binding.txvcodigo.text = revistaEntity.getCode().toString()
                binding.txvTitulo.text = revistaEntity.getTitle()
                binding.txvAnio.text = revistaEntity.getYearPub().toString()
                binding.txvTipoPublicacion.text = context.resources.getString(R.string.revista_tag)
                // Se carga el numero de revista
                binding.txvEstado.text = revistaEntity.getNumRev().toString()
            }
        }
    }

    override fun getItemCount(): Int = lstPublicaciones.size

    fun setLibros(libros: MutableList<LibroEntity>) {
        this.lstPublicaciones = libros.toMutableList()
        notifyDataSetChanged()
    }

    fun setRevistas(revistas: MutableList<RevistaEntity>) {
        this.lstPublicaciones = revistas.toMutableList()
        notifyDataSetChanged()
    }

    fun updateLibro(libroEntity: LibroEntity) {
        val index = lstPublicaciones.indexOf(libroEntity)
        if(index != -1){
            lstPublicaciones[index] = libroEntity
            notifyItemChanged(index)
        }
    }

    fun deleteLibro(libroEntity: LibroEntity) {
        val index = lstPublicaciones.indexOf(libroEntity)
        if(index != -1){
            lstPublicaciones.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun deleteRevista(revistaEntity: RevistaEntity) {
        val index = lstPublicaciones.indexOf(revistaEntity)
        if(index != -1){
            lstPublicaciones.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPublicacionListBinding.bind(view)

        fun setListener(libroEntity: LibroEntity, position: Int){
            binding.root.setOnClickListener{
                listener.onClickListener(libroEntity , position)
            }
            binding.root.setOnLongClickListener {
                listener.onDeleteLibro(libroEntity, position)
                true
            }
        }

        fun setListenerRevista(revistaEntity: RevistaEntity, position: Int){
            with(binding.root){
                setOnLongClickListener {
                    listener.onDeleteRevista(revistaEntity, position)
                    true
                }
            }
        }
    }
}
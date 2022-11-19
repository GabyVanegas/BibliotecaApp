package com.example.bibliotecaapp.Repository

import com.example.bibliotecaapp.Interfaces.UsuarioDao
import com.example.bibliotecaapp.Models.UsuarioEntity

class UsuarioRepository(private val dao: UsuarioDao) {
    suspend fun insert(usuario: UsuarioEntity) = dao.addUsuario(usuario)
}
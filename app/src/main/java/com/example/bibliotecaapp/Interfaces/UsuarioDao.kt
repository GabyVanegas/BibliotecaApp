package com.example.bibliotecaapp.Interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bibliotecaapp.Models.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)

}
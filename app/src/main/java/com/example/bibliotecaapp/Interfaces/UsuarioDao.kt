package com.example.bibliotecaapp.Interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bibliotecaapp.Models.UsuarioEntity

@Dao
interface UsuarioDao {
    @Query("SELECT EXISTS (SELECT * FROM UsuarioEntity WHERE username=:username AND password=:password)")
    fun login(username: String, password: String): Boolean


    @Query("SELECT * FROM UsuarioEntity")
    fun getAll(): List<UsuarioEntity>
    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)

}
package com.example.bibliotecaapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bibliotecaapp.Interfaces.LibroDao
import com.example.bibliotecaapp.Interfaces.RevistaDao
import com.example.bibliotecaapp.Interfaces.UsuarioDao
import com.example.bibliotecaapp.Models.LibroEntity
import com.example.bibliotecaapp.Models.RevistaEntity
import com.example.bibliotecaapp.Models.UsuarioEntity

@Database(entities = [LibroEntity::class, RevistaEntity::class, UsuarioEntity::class], version = 1)
abstract class BibliotecaDatabase : RoomDatabase(){

    abstract fun libroDao() : LibroDao
    abstract fun revistaDao() : RevistaDao
    abstract fun UsuarioDao() : UsuarioDao
}
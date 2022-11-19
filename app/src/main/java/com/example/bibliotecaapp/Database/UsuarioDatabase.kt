package com.example.bibliotecaapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bibliotecaapp.Interfaces.UsuarioDao
import com.example.bibliotecaapp.Models.UsuarioEntity

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class UsuarioDatabase: RoomDatabase() {
    abstract fun getUsuarioDao(): UsuarioDao
}
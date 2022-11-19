package com.example.bibliotecaapp.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuarioEntity")
class UsuarioEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "username") private var username: String,
    @ColumnInfo(name = "password") private var password: String) {

    fun getUsername(): String = this.username
    fun getPassword(): String = this.password
}
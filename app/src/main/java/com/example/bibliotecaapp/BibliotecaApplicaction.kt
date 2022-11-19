package com.example.bibliotecaapp

import android.app.Application
import androidx.room.Room
import com.example.bibliotecaapp.Database.BibliotecaDatabase

class BibliotecaApplicaction : Application()  {

    companion object {
        lateinit var database: BibliotecaDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, BibliotecaDatabase::class.java,
            "BibliotecaDB")
            .build()
    }
}
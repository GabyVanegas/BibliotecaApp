package com.example.bibliotecaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bibliotecaapp.Models.UsuarioEntity
import com.example.bibliotecaapp.databinding.ActivityRegistrarUsuarioBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RegistrarUsuarioActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityRegistrarUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Habilitar el action supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Registrar Usuario"

        binding.btnSave.setOnClickListener(this)

    }

    override fun onClick(p0: View?){
        when(p0!!.id){
            binding.btnSave.id->{
                if(binding.edtUserName.text.toString().isNotEmpty() && binding.edtPassword.text.toString().isNotEmpty()) doAsync{
                        BibliotecaApplicaction.database.UsuarioDao().addUsuario(
                            UsuarioEntity(
                                username = binding.edtUserName.text.toString(),
                                password = binding.edtPassword.text.toString()
                            )
                        )
                    uiThread {
                        LoginStartActivity()
                    }
                }
                configProgressDialog()
                Toast.makeText(this, "!Registro exitoso¡", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método que configura el action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // Finaliza la actividad
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configSharedPreference(){
        val preferences = getSharedPreferences("Users", MODE_PRIVATE)

        with(preferences.edit()){
            putString("username", binding.edtUserName.text.toString())
            putString("password", binding.edtPassword.text.toString())
                .apply()
        }
    }
    fun LoginStartActivity(){
        startActivity(Intent(this, LoginActivity::class.java))
    }
    fun configProgressDialog(){
        val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        alertBuilder.setView(dialogView)
        alertBuilder.setCancelable(false)
        val dialog = alertBuilder.create()
        dialog.show()
        // Configurando hilo, para asignar tiempo
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            finish()
        }, 3000)
    }
}
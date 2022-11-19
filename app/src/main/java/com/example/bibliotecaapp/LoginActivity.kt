package com.example.bibliotecaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bibliotecaapp.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Iniciar Sesión"

        binding.btnSignUp.setOnClickListener{
            startActivity(Intent(this, RegistrarUsuarioActivity::class.java))
        }

        binding.btnSave.setOnClickListener {
            login()
        }

        configGlide()
    }

    private fun login(){

        doAsync {
          try {
              val userName: String = binding.edtUserName.text.toString()
              val password: String = binding.edtPassword.text.toString()
              var existe = false;


              if (BibliotecaApplicaction.database.UsuarioDao().login(userName, password)) {
                  existe = true
              }
              uiThread {
               //   Toast.makeText(this@LoginActivity, "Metodo entrando"+existe, Toast.LENGTH_SHORT).show()
                  if (existe) {
                      startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                  } else {
                      Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                  }
              }

          }catch (e:Exception){
              Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
          }
        }
    }


    private fun configGlide(){
        val url = "https://yt3.ggpht.com/ytc/AMLnZu9-hbX2bZLgbBKPQ9P1sSg9U0wL44dmcHLcSX5BvQ=s900-c-k-c0x00ffffff-no-rj"
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .circleCrop()
            .into(binding.imgvIconApp)
    }

}
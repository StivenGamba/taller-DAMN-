package com.example.prueba_ext

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecuperarContrasenaActivity : AppCompatActivity() {

    private lateinit var edTextCorreo : EditText

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btnEnviar: Button
    private lateinit var txtIniciasesion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperarcontrasena)

        Log.d("RecuperarContrasenaActivity", "OnCreate: inicializamos el Activity Recuperar Contraseña")
        edTextCorreo = findViewById(R.id.ed_correoRecontrasena)
        btnEnviar = findViewById(R.id.btn_enviarRecontrasena)
        txtIniciasesion = findViewById(R.id.txt_iniciaSesionRecuperar)

        //Archivo de almacenamiento local
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        btnEnviar.setOnClickListener {
            //Validaciòn
            if (validarCampos()){
                //guardar datos
                enviarCorreo()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                //redirecionar al login
            }
        }

        txtIniciasesion.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                //redirecionar al login
        }
    }

    private fun enviarCorreo() {
        val correo = sharedPreferences.getString("correo", "")

        if (correo == edTextCorreo.text.toString()) {
            // Código cuando la validación es correcta
            Toast.makeText(
                this,
                "Enviamos un correo a " + correo + " para la resuperacion de contraseña",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else {
            Toast.makeText(this, "El correo no se encuentra registrado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarCampos(): Boolean{
        val correo = edTextCorreo.text.toString().trim()

     if(correo.isEmpty()){
            Toast.makeText(this, "El campo correo es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }


}



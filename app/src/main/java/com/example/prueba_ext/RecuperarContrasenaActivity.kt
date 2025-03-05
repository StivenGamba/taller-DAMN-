package com.example.prueba_ext

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecuperarContrasenaActivity : AppCompatActivity() {

    private lateinit var edTextCorreo : EditText

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperarcontrasena)

        Log.d("RecuperarContrasenaActivity", "OnCreate: inicializamos el Activity Recuperar Contraseña")
        edTextCorreo = findViewById(R.id.ed_correoRecontrasena)
        btnEnviar = findViewById(R.id.btn_enviarRecontrasena)

        //Archivo de almacenamiento local
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        btnEnviar.setOnClickListener {
            //Validaciòn
            if (validarCampos()){
                //guardar datos
                guardarDatos()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                //redirecionar al login
            }
        }
    }

    private fun guardarDatos(){
        val editor = sharedPreferences.edit()

        editor.putString("correo", edTextCorreo.text.toString().trim())

        editor.apply()

        Toast.makeText(this, "Se ha enviado el correo exitosamente", Toast.LENGTH_SHORT).show()
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



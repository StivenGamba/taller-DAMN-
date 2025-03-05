package com.example.prueba_ext

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity(){

    private lateinit var et_correo : EditText
    private lateinit var et_contrasena : EditText
    private lateinit var btn_Ingresar : Button
    private lateinit var txtRecuContrasena : TextView
    private lateinit var btn_IngresaGoogle : Button
    private lateinit var txt_registrate : TextView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Log.d("LoginActivity", "OnCreate: inicializamos el Activity Login")

        et_correo = findViewById(R.id.et_Nameuser)
        et_contrasena = findViewById(R.id.et_contrasena)
        btn_Ingresar = findViewById(R.id.btn_ingresa)
        txtRecuContrasena = findViewById(R.id.txt_recuperarContrasena)
        btn_IngresaGoogle = findViewById(R.id.btn_ingoogle)
        txt_registrate = findViewById(R.id.txt_registrate)

        //Archivo de almacenamiento local
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        btn_Ingresar.setOnClickListener {
            // Validación
            if (validarCampos()) {
                if (autenticarUsuario()) {
                    val intent = Intent(this, PerfilActivity::class.java) //
                    startActivity(intent)
                    finish()
                }
            }
        }


        txtRecuContrasena.setOnClickListener{
            val intent = Intent(this, RecuperarContrasenaActivity::class.java)
            startActivity(intent)
            finish()
        }

        txt_registrate.setOnClickListener{
            val intent2 = Intent(this, RegistroActivity::class.java)
            startActivity(intent2)
            finish()
        }



    }

    private fun autenticarUsuario(): Boolean {
        val correoIngresado = et_correo.text.toString().trim()
        val contrasenaIngresada = et_contrasena.text.toString().trim()

        val correoGuardado = sharedPreferences.getString("correo", "")
        val contrasenaGuardada = sharedPreferences.getString("contrasena", "")

        return if (correoIngresado == correoGuardado && contrasenaIngresada == contrasenaGuardada) {
            true
        } else {
            Toast.makeText(this, "Correo o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
            false
        }
    }


    private fun validarCampos(): Boolean{
        val nombre = et_correo.text.toString().trim()
        val contrasena = et_contrasena.text.toString().trim()

        if(nombre.isEmpty()){
            Toast.makeText(this, "El campo usuario es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        } else if(contrasena.isEmpty()){
            Toast.makeText(this, "El campo contraseña es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }
}
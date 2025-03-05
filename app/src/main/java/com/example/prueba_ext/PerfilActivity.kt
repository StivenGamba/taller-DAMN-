package com.example.prueba_ext

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {


    private lateinit var btnEditar: Button
    private lateinit var Textnombre : TextView
    private lateinit var TextApellido : TextView
    private lateinit var TextCorreo : TextView
    private lateinit var TextTelefono : TextView
    private lateinit var btn_cerrarSesion : Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        Log.d("PerfilActivity", "OnCreate: inicializamos el Activity perfil")

        // Inicializar las vistas
        btnEditar = findViewById(R.id.btn_editar)
        Textnombre = findViewById(R.id.txt_NombreUser)
        TextApellido = findViewById(R.id.txt_ApellidoUser)
        TextCorreo = findViewById(R.id.txt_correoUser)
        TextTelefono = findViewById(R.id.txt_TelefonoUser)
        btn_cerrarSesion = findViewById(R.id.btn_CerrarSesion)

        cargarDatosusuario()

        btnEditar.setOnClickListener {
            //Redirigir a la actividad de editar perfil
                finish()
        }

        btn_cerrarSesion.setOnClickListener{
            mostrarDialogoCerrarSesion()
        }

    }

    private fun mostrarDialogoCerrarSesion() {
        AlertDialog.Builder(this)
            .setTitle("Cerrar sesión")
            .setMessage("¿Estás seguro de que deseas cerrar sesión?")
            .setPositiveButton("Sí") { _, _ -> //función lambda
                cerrarSesion()
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun cerrarSesion() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun cargarDatosusuario(){

        val nombres = sharedPreferences.getString("nombre", "")
        val apellidos = sharedPreferences.getString("apellido", "")
        val correo = sharedPreferences.getString("correo", "")
        val telefeno = sharedPreferences.getString("telefono", "")

        Textnombre.text = nombres
        TextApellido.text = apellidos
        TextCorreo.text = correo
        TextTelefono.text = telefeno

    }



}
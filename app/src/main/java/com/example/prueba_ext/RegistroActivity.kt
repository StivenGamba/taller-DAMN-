package com.example.prueba_ext

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var edTextnombre : EditText
    private lateinit var edTextApellido : EditText
    private lateinit var edTextCorreo : EditText
    private lateinit var edTextTelefono : EditText
    private lateinit var edTextContrasena : EditText
    private lateinit var edTextRepetirContrasena : EditText
    private lateinit var btnRegistrate: Button
    private lateinit var cboxTYC: CheckBox

    private lateinit var sharedPreferences: SharedPreferences

     override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        Log.d("RegistroActivity", "OnCreate: inicializamos el Activity Registro")

         edTextnombre = findViewById(R.id.etNombreRegistro)
         edTextApellido = findViewById(R.id.etApellidoRegistro)
         edTextCorreo = findViewById(R.id.etCorreoRegistro)
         edTextTelefono = findViewById(R.id.etTelefonoRegistro)
         edTextContrasena = findViewById(R.id.etContrasenaRegistro)
         edTextRepetirContrasena = findViewById(R.id.etConfirmarRegistro)
         btnRegistrate = findViewById(R.id.btnRestrateRegistro)
         cboxTYC = findViewById(R.id.cboxTYC)

         //Archivo de almacenamiento local
         sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

         btnRegistrate.setOnClickListener {
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

        editor.putString("nombre", edTextnombre.text.toString().trim())
        editor.putString("apellido", edTextApellido.text.toString().trim())
        editor.putString("correo", edTextCorreo.text.toString().trim())
        editor.putString("telefono", edTextTelefono.text.toString().trim())
        editor.putString("contrasena", edTextContrasena.text.toString().trim())
        editor.putString("reContrasena", edTextRepetirContrasena.text.toString().trim())
        editor.putString("tyc", cboxTYC.text.toString().trim())

        editor.apply()

        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
    }

    private fun validarCampos(): Boolean{
        val nombre = edTextnombre.text.toString().trim()
        val apellidos = edTextApellido.text.toString().trim()
        val correo = edTextCorreo.text.toString().trim()
        val telefono = edTextTelefono.text.toString().trim()
        val contrasena = edTextContrasena.text.toString().trim()
        val reContrasena = edTextRepetirContrasena.text.toString().trim()
        val tyc = cboxTYC.text.toString().trim()

        if(nombre.isEmpty()){
            Toast.makeText(this, "El campo nombres es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        } else if(apellidos.isEmpty()){
            Toast.makeText(this, "El campo apellidos es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        } else if(correo.isEmpty()){
            Toast.makeText(this, "El campo correo es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        } else if(telefono.isEmpty()){
            Toast.makeText(this, "El campo teléfono es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        } else if(contrasena.isEmpty()){
            Toast.makeText(this, "El campo contraseña es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        } else if(reContrasena.isEmpty()){
            Toast.makeText(this, "El campo confirmar contraseña es obligatorio.", Toast.LENGTH_SHORT).show()
            return false
        } else if(tyc.isEmpty()){
            Toast.makeText(this, "Debes aceptar los Términos & Condiciones para continuar con el registro.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }


}
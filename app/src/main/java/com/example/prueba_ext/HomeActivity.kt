package com.example.prueba_ext

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var btnComienza: Button
    private lateinit var txt_registrate : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("HomeActivity", "OnCreate: inicializamos el Activity Home")

        btnComienza = findViewById(R.id.btn_comienzaHome)
        txt_registrate = findViewById(R.id.txt_registrateHome)

        btnComienza.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        txt_registrate.setOnClickListener{
            val intent2 = Intent(this, RegistroActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }
}
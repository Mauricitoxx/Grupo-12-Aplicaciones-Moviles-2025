package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerlayout)

        val nombreEdit = findViewById<EditText>(R.id.etRegisterEmail)
        val emailEdit = findViewById<EditText>(R.id.etRegisterEmail1)
        val contraseniaEdit = findViewById<EditText>(R.id.etRegisterPassword)
        val repcontraseniaEdit = findViewById<EditText>(R.id.etRegisterPassword1)
        val registerButton = findViewById<Button>(R.id.btnConfirmRegister)
        val iniciosesionButton = findViewById<Button>(R.id.btnBackRegister)

        registerButton.setOnClickListener setOnClickListenerOn@{
            val nombre = nombreEdit.text.toString().trim()
            val email = emailEdit.text.toString().trim()
            val contrasenia = contraseniaEdit.text.toString().trim()
            val repecontrasenia = repcontraseniaEdit.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || contrasenia.isEmpty() || repecontrasenia.isEmpty()){
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListenerOn
            }

            if (contrasenia.trim() != repecontrasenia.trim()){
                Toast.makeText(this, "La contraseÃ±a y su repeticion no son iguales, ingrese nuevamente.", Toast.LENGTH_SHORT).show()
                return@setOnClickListenerOn
            }else{
                Toast.makeText(this, "El usuario se registro con exito", Toast.LENGTH_SHORT).show()
            }
        }

        iniciosesionButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OlvidarContraseniaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.olvidarcontrasenia)

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val olvidarButton = findViewById<Button>(R.id.button3)
        val registarButton = findViewById<Button>(R.id.button4)

        olvidarButton.setOnClickListener setOnClickListenerOn@{
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()){
                Toast.makeText(this, "Por favor, ingrese su email", Toast.LENGTH_SHORT).show()
                return@setOnClickListenerOn
            }else{
                Toast.makeText(this, "Se envio un mensaje por email para cambiar la contraseña", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        registarButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
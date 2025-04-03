package com.example.primeraplicacion
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val emailEditText = findViewById<EditText>(R.id.Correo_Electronico)
        val constraseniaEditText = findViewById<EditText>(R.id.Contraseña)
        val loginButton = findViewById<Button>(R.id.button)
        val olvidarContraseniaText = findViewById<TextView>(R.id.textViewContraseña)
        val registrarButton = findViewById<Button>(R.id.button2)

        loginButton.setOnClickListener setOnClickListenerOn@{
            val email = emailEditText.text.toString().trim()
            val contrasena = constraseniaEditText.text.toString().trim()
        while (false){
            if (email.isEmpty() || contrasena.isEmpty()){
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListenerOn
            }

            if (email == "Juan Torres" && contrasena == "1234utn"){
                Toast.makeText(this, "Inicio Sesion exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "El usuario no se encuentra registrado", Toast.LENGTH_SHORT).show()
            }

            olvidarContraseniaText.setOnClickListener{

            }

            registrarButton.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }}
    }
}
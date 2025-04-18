package com.example.myapplication
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color.RED
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginlayout)
        val emailEditText = findViewById<EditText>(R.id.Correo_Electronico)
        val constraseniaEditText = findViewById<EditText>(R.id.Contraseña)
        val loginButton = findViewById<Button>(R.id.button)
        val olvidarContraseniaText = findViewById<TextView>(R.id.textViewContraseña)
        val registrarButton = findViewById<Button>(R.id.button2)

        loginButton.setOnClickListener setOnClickListenerOn@{
            val email = emailEditText.text.toString().trim()
            val contrasena = constraseniaEditText.text.toString().trim()
            if (email.isEmpty() || contrasena.isEmpty()){

                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
                return@setOnClickListenerOn
            }


            if (email == "Juan Torres" && contrasena == "1234utn"){
                val intent = Intent(this, MainActivity::class.java);
                intent.putExtra("nombre",email);
                startActivity(intent)

                Toast.makeText(this, "Inicio Sesion exitoso", Toast.LENGTH_LONG).show()

                finish()
            } else {
                Toast.makeText(this, "El usuario no se encuentra registrado", Toast.LENGTH_LONG).show()
            }
        }

        olvidarContraseniaText.setOnClickListener{
            val intent = Intent(this, OlvidarContraseniaActivity::class.java)
            startActivity(intent)
        }

        registrarButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
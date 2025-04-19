package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginlayout)

        val emailEditText = findViewById<EditText>(R.id.Correo_Electronico)
        val constraseniaEditText = findViewById<EditText>(R.id.Contraseña)
        val loginButton = findViewById<Button>(R.id.button)
        val olvidarContraseniaText = findViewById<TextView>(R.id.textViewContraseña)
        val registrarButton = findViewById<Button>(R.id.button2)

        // 👁️ Mostrar/ocultar contraseña
        constraseniaEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                val drawable = constraseniaEditText.compoundDrawables[drawableEnd]
                drawable?.let {
                    val bounds = it.bounds
                    val touchAreaStart = constraseniaEditText.width - constraseniaEditText.paddingEnd - bounds.width()
                    if (event.x >= touchAreaStart) {
                        isPasswordVisible = !isPasswordVisible
                        if (isPasswordVisible) {
                            constraseniaEditText.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                            constraseniaEditText.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, R.drawable.ic_visibility, 0
                            )
                        } else {
                            constraseniaEditText.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                            constraseniaEditText.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, R.drawable.ic_visibility_off, 0
                            )
                        }
                        constraseniaEditText.setSelection(constraseniaEditText.text.length)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

        loginButton.setOnClickListener setOnClickListenerOn@{
            val email = emailEditText.text.toString().trim()
            val contrasena = constraseniaEditText.text.toString().trim()
            if (email.isEmpty() && contrasena.isEmpty()){
                emailEditText.setBackgroundResource(R.drawable.borderbox)
                constraseniaEditText.setBackgroundResource(R.drawable.borderbox)
                Toast.makeText(this, "Por favor, complete el usuario y contraseña", Toast.LENGTH_LONG).show()
                return@setOnClickListenerOn
            }else if (email.isEmpty()){
                emailEditText.setBackgroundResource(R.drawable.borderbox)
                Toast.makeText(this, "Por favor, complete el usuario", Toast.LENGTH_LONG).show()
                return@setOnClickListenerOn
            }else if(contrasena.isEmpty()){
                constraseniaEditText.setBackgroundResource(R.drawable.borderbox)
                Toast.makeText(this, "Por favor, complete la contraseña", Toast.LENGTH_LONG).show()
                return@setOnClickListenerOn
            } else{
                if (email.length<6 && contrasena.length < 6){
                    emailEditText.setBackgroundResource(R.drawable.borderbox)
                    constraseniaEditText.setBackgroundResource(R.drawable.borderbox)
                    Toast.makeText(this, "el usuario y contraseña deben tener mas de 6 caracteres", Toast.LENGTH_SHORT).show()
                }
                if (email.length<6) {
                    emailEditText.setBackgroundResource(R.drawable.borderbox)
                    Toast.makeText(this, "el usuario debe tener mas de 6 caracteres", Toast.LENGTH_SHORT).show()
                }else if (contrasena.length < 6) {
                    constraseniaEditText.setBackgroundResource(R.drawable.borderbox)
                    Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                }else{
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

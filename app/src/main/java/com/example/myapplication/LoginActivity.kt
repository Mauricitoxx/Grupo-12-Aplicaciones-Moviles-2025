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
        val emailErrorText = findViewById<TextView>(R.id.emailErrorText)
        val passwordErrorText = findViewById<TextView>(R.id.passwordErrorText)
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

            emailErrorText.visibility = TextView.GONE
            passwordErrorText.visibility = TextView.GONE

            var hasError = false

            if (email.isEmpty()) {
                emailEditText.setBackgroundResource(R.drawable.borderbox)
                emailErrorText.text = "Por favor, complete el usuario"
                emailErrorText.visibility = TextView.VISIBLE
                hasError = true
            }

            if (contrasena.isEmpty()) {
                constraseniaEditText.setBackgroundResource(R.drawable.borderbox)
                passwordErrorText.text = "Por favor, complete la contraseña"
                passwordErrorText.visibility = TextView.VISIBLE
                hasError = true
            }

            if (hasError) return@setOnClickListenerOn

            if (email.length < 6) {
                emailEditText.setBackgroundResource(R.drawable.borderbox)
                emailErrorText.text = "El usuario debe tener más de 6 caracteres"
                emailErrorText.visibility = TextView.VISIBLE
                hasError = true
            }

            if (contrasena.length < 6) {
                constraseniaEditText.setBackgroundResource(R.drawable.borderbox)
                passwordErrorText.text = "La contraseña debe tener al menos 6 caracteres"
                passwordErrorText.visibility = TextView.VISIBLE
                hasError = true
            }

            if (hasError) return@setOnClickListenerOn

            if (email == "Juan Torres" && contrasena == "1234utn") {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("nombre", email)
                startActivity(intent)
                Toast.makeText(this, "Inicio Sesion exitoso", Toast.LENGTH_LONG).show()
                finish()
            } else {
                emailEditText.setBackgroundResource(R.drawable.borderbox)
                constraseniaEditText.setBackgroundResource(R.drawable.borderbox)
                passwordErrorText.text = "No se pudo iniciar sesión porque no existe cuenta con ese mail"
                passwordErrorText.visibility = TextView.VISIBLE
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

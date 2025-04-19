package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private var isPasswordVisible = false
    private var isRepeatPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerlayout)

        val nombreEdit = findViewById<EditText>(R.id.etRegisterEmail)
        val emailEdit = findViewById<EditText>(R.id.etRegisterEmail1)
        val contraseniaEdit = findViewById<EditText>(R.id.etRegisterPassword)
        val repcontraseniaEdit = findViewById<EditText>(R.id.etRegisterPassword1)
        val registerButton = findViewById<Button>(R.id.btnConfirmRegister)
        val iniciosesionButton = findViewById<Button>(R.id.btnBackRegister)

        // Mostrar/ocultar contraseña principal
        contraseniaEdit.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                if (event.rawX >= (contraseniaEdit.right - contraseniaEdit.compoundDrawables[drawableEnd].bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    if (isPasswordVisible) {
                        contraseniaEdit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        contraseniaEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0)
                    } else {
                        contraseniaEdit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        contraseniaEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0)
                    }
                    contraseniaEdit.setSelection(contraseniaEdit.text.length)
                    return@setOnTouchListener true
                }
            }
            false
        }

        // Mostrar/ocultar repetir contraseña
        repcontraseniaEdit.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                if (event.rawX >= (repcontraseniaEdit.right - repcontraseniaEdit.compoundDrawables[drawableEnd].bounds.width())) {
                    isRepeatPasswordVisible = !isRepeatPasswordVisible
                    if (isRepeatPasswordVisible) {
                        repcontraseniaEdit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        repcontraseniaEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0)
                    } else {
                        repcontraseniaEdit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        repcontraseniaEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0)
                    }
                    repcontraseniaEdit.setSelection(repcontraseniaEdit.text.length)
                    return@setOnTouchListener true
                }
            }
            false
        }

        registerButton.setOnClickListener setOnClickListenerOn@{
            val nombre = nombreEdit.text.toString().trim()
            val email = emailEdit.text.toString().trim()
            val contrasenia = contraseniaEdit.text.toString().trim()
            val repecontrasenia = repcontraseniaEdit.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || contrasenia.isEmpty() || repecontrasenia.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListenerOn
            }

            if (contrasenia.length < 6 || repecontrasenia.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show()
                return@setOnClickListenerOn
            }

            if (contrasenia != repecontrasenia) {
                Toast.makeText(this, "La contraseña y su repetición no son iguales, ingrese nuevamente.", Toast.LENGTH_SHORT).show()
                return@setOnClickListenerOn
            } else {
                Toast.makeText(this, "El usuario se registró con éxito", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        iniciosesionButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

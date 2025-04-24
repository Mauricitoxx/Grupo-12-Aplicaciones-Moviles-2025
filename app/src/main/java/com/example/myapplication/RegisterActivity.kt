package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.*
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

        val errorNombre = findViewById<TextView>(R.id.tvErrorNombre)
        val errorEmail = findViewById<TextView>(R.id.tvErrorEmail1)
        val errorPass = findViewById<TextView>(R.id.tvErrorPass)
        val errorRepeatPass = findViewById<TextView>(R.id.tvErrorRepeatPass)

        val defaultBackground = nombreEdit.background

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

            errorNombre.visibility = TextView.GONE
            errorEmail.visibility = TextView.GONE
            errorPass.visibility = TextView.GONE
            errorRepeatPass.visibility = TextView.GONE

            nombreEdit.background = defaultBackground
            emailEdit.background = defaultBackground
            contraseniaEdit.background = defaultBackground
            repcontraseniaEdit.background = defaultBackground

            var hayError = false

            if (nombre.isEmpty()) {
                errorNombre.text = "El nombre es obligatorio"
                errorNombre.visibility = TextView.VISIBLE
                nombreEdit.setBackgroundResource(R.drawable.borderbox)
                hayError = true
            }

            if (email.isEmpty()) {
                errorEmail.text = "El correo es obligatorio"
                errorEmail.visibility = TextView.VISIBLE
                emailEdit.setBackgroundResource(R.drawable.borderbox)
                hayError = true
            }

            if (contrasenia.isEmpty()) {
                errorPass.text = "La contraseña es obligatoria"
                errorPass.visibility = TextView.VISIBLE
                contraseniaEdit.setBackgroundResource(R.drawable.borderbox)
                hayError = true
            }

            if (repecontrasenia.isEmpty()) {
                errorRepeatPass.text = "Debe repetir la contraseña"
                errorRepeatPass.visibility = TextView.VISIBLE
                repcontraseniaEdit.setBackgroundResource(R.drawable.borderbox)
                hayError = true
            }

            if (contrasenia.length < 6) {
                errorPass.text = "Debe tener al menos 6 caracteres"
                errorPass.visibility = TextView.VISIBLE
                contraseniaEdit.setBackgroundResource(R.drawable.borderbox)
                hayError = true
            }

            if (repecontrasenia.length < 6) {
                errorRepeatPass.text = "Debe tener al menos 6 caracteres"
                errorRepeatPass.visibility = TextView.VISIBLE
                repcontraseniaEdit.setBackgroundResource(R.drawable.borderbox)
                hayError = true
            }

            if (contrasenia != repecontrasenia) {
                errorRepeatPass.text = "Las contraseñas no coinciden"
                errorRepeatPass.visibility = TextView.VISIBLE
                contraseniaEdit.setBackgroundResource(R.drawable.borderbox)
                repcontraseniaEdit.setBackgroundResource(R.drawable.borderbox)
                hayError = true
            }

            if (hayError) return@setOnClickListenerOn

            Toast.makeText(this, "El usuario se registró con éxito", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        iniciosesionButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

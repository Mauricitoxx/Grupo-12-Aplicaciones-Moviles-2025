package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bienvenida)

        val AndroidButton = findViewById<ImageButton>(R.id.buttonAndroid)
        val IOSButton = findViewById<ImageButton>(R.id.buttonIOS)
        val Preferencia1 = findViewById<Switch>(R.id.switch1)
        val Preferencia2 = findViewById<Switch>(R.id.switch2)
        val Preferencia3 = findViewById<Switch>(R.id.switch3)
        val Preferencia4 = findViewById<Switch>(R.id.switch4)
        val OtroButton = findViewById<Button>(R.id.buttonOtro)
        val opcionesExtras = mutableListOf<String>()
        val botonSalir = findViewById<Button>(R.id.buttonSalir)
        val botonGuardar = findViewById<Button>(R.id.buttonGuardar)
        var plataformaSeleccionada: String? = null


        fun resaltarSeleccionPlataforma() {
            AndroidButton.setBackgroundResource(if (plataformaSeleccionada == "Android") R.drawable.boton_redondo_1 else R.drawable.boton_redondo_2)
            IOSButton.setBackgroundResource(if (plataformaSeleccionada == "IOS") R.drawable.boton_redondo_1 else R.drawable.boton_redondo_2)

        }

        AndroidButton.setOnClickListener {
            plataformaSeleccionada = "Android"
            resaltarSeleccionPlataforma()
            Toast.makeText(this, "Plataforma seleccionada: Android", Toast.LENGTH_SHORT).show()
        }

        IOSButton.setOnClickListener {
            plataformaSeleccionada = "IOS"
            resaltarSeleccionPlataforma()
            Toast.makeText(this, "Plataforma seleccionada: IOS", Toast.LENGTH_SHORT).show()
        }

        OtroButton.setOnClickListener {
            val input = EditText(this)
            input.hint = "Escribe una nueva opción"

            val container = LinearLayout(this)
            container.orientation = LinearLayout.VERTICAL
            container.setPadding(50, 30, 50, 10)
            container.addView(input)

            val dialog = AlertDialog.Builder(this)
                .setTitle("Nueva opción")
                .setMessage("Por favor, ingrese una nueva opción:")
                .setView(container)
                .setPositiveButton("Aceptar") { _, _ ->
                    val nuevaOpcion = input.text.toString().trim()
                    if (nuevaOpcion.isNotEmpty()) {
                        opcionesExtras.add(nuevaOpcion)
                        Toast.makeText(this, "Opción agregada: $nuevaOpcion", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "No se ingresó ninguna opción", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancelar", null)
                .create()

            dialog.setOnShowListener {
                dialog.window?.setBackgroundDrawableResource(R.color.Yellow1)

                val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

                positiveButton.setTextColor(ContextCompat.getColor(this, R.color.Green1))
                negativeButton.setTextColor(ContextCompat.getColor(this, R.color.Green1))
            }

            dialog.show()
        }

        botonGuardar.setOnClickListener {
            if (plataformaSeleccionada == null) {
                Toast.makeText(this, "Por favor, seleccione una plataforma", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val preferencias = mutableListOf<String>()
            if (Preferencia1.isChecked) preferencias.add("Programación")
            if (Preferencia2.isChecked) preferencias.add("Redes")
            if (Preferencia3.isChecked) preferencias.add("Seguridad")
            if (Preferencia4.isChecked) preferencias.add("Hardware")

            val mensaje = StringBuilder()
            mensaje.append("Plataforma: $plataformaSeleccionada\n")
            mensaje.append("Preferencias: ${if (preferencias.isNotEmpty()) preferencias.joinToString(", ") else "Ninguna"}\n")
            mensaje.append("Otras opciones: ${if (opcionesExtras.isNotEmpty()) opcionesExtras.joinToString(", ") else "Ninguna"}")


            val dialog = AlertDialog.Builder(this)
                .setTitle("Sus preferencias")
                .setMessage(mensaje.toString())
                .setPositiveButton("OK", null)
                .create()

            dialog.setOnShowListener {
                dialog.window?.setBackgroundDrawableResource(R.color.Yellow1)

                val okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                okButton.setTextColor(ContextCompat.getColor(this, R.color.Green1))
            }

            dialog.show()


            Preferencia1.isChecked = false
            Preferencia2.isChecked = false
            Preferencia3.isChecked = false
            Preferencia4.isChecked = false
            plataformaSeleccionada = null
            opcionesExtras.clear()
            resaltarSeleccionPlataforma()
        }

        botonSalir.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val nom = intent.getStringExtra("Nombre") ?: "Juan Torres"
        val textoUsuario = findViewById<TextView>(R.id.textViewBienvenido)
        textoUsuario.text = "Bienvenido, $nom"
    }
}

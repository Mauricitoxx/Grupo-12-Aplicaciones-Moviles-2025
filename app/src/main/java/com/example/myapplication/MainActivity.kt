package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import android.annotation.SuppressLint
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(){
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bienvenida)

        val AndroidButton = findViewById<Button>(R.id.buttonAndroid)
        val IOSButton = findViewById<Button>(R.id.buttonIOS)
        val Preferencia1 = findViewById<Switch>(R.id.switch1)
        val Preferencia2 = findViewById<Switch>(R.id.switch2)
        val Preferencia3 = findViewById<Switch>(R.id.switch3)
        val Preferencia4 = findViewById<Switch>(R.id.switch4)
        val OtroButton = findViewById<Button>(R.id.buttonOtro)
        val opcionesExtras = mutableListOf<String>()
        val botonSalir = findViewById<Button>(R.id.buttonSalir)
        val botonGuardar = findViewById<Button>(R.id.buttonGuardar)
        var plataformaSeleccionada: String? = null

        fun resaltarSeleccionPlataforma(){
            AndroidButton.setBackgroundColor(ContextCompat.getColor(this, if (plataformaSeleccionada=="Android") R.color.teal_200 else android.R.color.darker_gray))
            IOSButton.setBackgroundColor(ContextCompat.getColor(this, if (plataformaSeleccionada=="IOS") R.color.teal_200 else android.R.color.darker_gray))
        }

        AndroidButton.setOnClickListener {
            plataformaSeleccionada = "Android"
            resaltarSeleccionPlataforma()
            Toast.makeText(this, "Plataforma seleccionada: Android", Toast.LENGTH_LONG).show()
        }

        IOSButton.setOnClickListener {
            plataformaSeleccionada = "IOS"
            resaltarSeleccionPlataforma()
            Toast.makeText(this, "Plataforma seleccionada: IOS", Toast.LENGTH_LONG).show()
        }

        OtroButton.setOnClickListener {
            val input = EditText(this)
            input.hint = "Escribe tu nueva opción"

            val dialog = AlertDialog.Builder(this)
                .setTitle("Nueva opción")
                .setMessage("Por favor, ingrese una nueva opción:")
                .setView(input)
                .setPositiveButton("Aceptar") { _, _ ->
                    val nuevaOpcion = input.text.toString().trim()
                    if (nuevaOpcion.isNotEmpty()) {
                        opcionesExtras.add(nuevaOpcion)
                        Toast.makeText(this, "Opción agregada: $nuevaOpcion", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "No se ingreso ninguna opción", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()
        }

        botonGuardar.setOnClickListener {
            if (plataformaSeleccionada == null){
                Toast.makeText(this, "Por favor, selecciones una plataforma", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val preferencias = mutableListOf<String>()
            if (Preferencia1.isChecked) preferencias.add("Programacion")
            if (Preferencia2.isChecked) preferencias.add("Redes")
            if (Preferencia3.isChecked) preferencias.add("Seguridad")
            if (Preferencia4.isChecked) preferencias.add("Hardware")

            val mensaje = StringBuilder()
            mensaje.append("Plataforma: $plataformaSeleccionada\n")
            mensaje.append("Preferencias: ${if(preferencias.isNotEmpty()) preferencias.joinToString(", ") else "Ninguna"}\n")
            mensaje.append("Otras opciones: ${if(opcionesExtras.isNotEmpty()) opcionesExtras.joinToString(",") else "Ninguna"}\n")

            Toast.makeText(this, mensaje.toString(), Toast.LENGTH_LONG).show()

            //Reiniciar todas las opciones
            Preferencia1.isChecked = false
            Preferencia2.isChecked = false
            Preferencia3.isChecked = false
            Preferencia4.isChecked = false
            plataformaSeleccionada = null
            opcionesExtras.clear()
        }

        botonSalir.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val nom=intent.getStringExtra("nombre") ?: "Invitado"
        val textoUsuario = findViewById<TextView>(R.id.textViewBienvenido);
        textoUsuario.setText("Bienvenido, " + nom);


    }
}
package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.appcompat.app.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bienvenida)
        val AndroidButton = findViewById<Button>(R.id.buttonAndroid)
        val IOSButton = findViewById<Button>(R.id.buttonIOS)
        val Switch1 = findViewById<Switch>(R.id.switch1)
        val Switch2 = findViewById<Switch>(R.id.switch2)
        val Switch3 = findViewById<Switch>(R.id.switch3)
        val Switch4 = findViewById<Switch>(R.id.switch4)
        val OtroButton = findViewById<Button>(R.id.buttonOtro)

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
                        Toast.makeText(this, "Opción agregada: $nuevaOpcion", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "No ingresaste ninguna opción", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancelar", null)
                .create()

            dialog.show()
        }
    }
}



//Version Original
// class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MyApplicationTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        Greeting("Android")
//    }
//}
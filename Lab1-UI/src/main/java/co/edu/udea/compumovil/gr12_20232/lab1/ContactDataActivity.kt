package co.edu.udea.compumovil.gr12_20232.lab1

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr12_20232.lab1.ui.theme.Labs20232Gr12Theme
import android.widget.AutoCompleteTextView;


class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContent {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.contact_data_layout)

            var paises_latam = arrayOf(
                "Bolivia", "Brasil", "Chile",
                "Colombia", "Costa Rica", "Cuba", "Ecuador", "El Salvador",
                "Guatemala", "Honduras", "México", "Nicaragua", "Panamá", "Paraguay",
                "Perú", "Puerto Rico", "República Dominicana", "Uruguay")

            var ciudades_colombia = arrayOf(
                "Bogotá", "Medellín", "Cali", "Santa Marta",
                "Barranquilla", "Villavicencio", "Bucaramanga", "Tunja"
            )

            val paises_adaptador = findViewById<AutoCompleteTextView>(R.id.textoPaises)

            paises_adaptador.setOnClickListener {
                paises_adaptador.requestFocus()
            }
            var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, paises_latam)
            paises_adaptador.threshold = 0
            paises_adaptador.setAdapter(adapter)

        }
    }

}


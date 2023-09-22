package co.edu.udea.compumovil.gr12_20232.lab1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
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
import java.util.Calendar

class MainActivity : ComponentActivity() {
    private lateinit var btnFecha: Button
    private lateinit var textofecha: EditText
    private lateinit var calendar: Calendar
    private lateinit var spinnerEducacion: Spinner
    private lateinit var textoSpinner: TextView
    private lateinit var campoNombre: EditText
    private lateinit var campoApellido: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_data_layout)

        //verificación de datos obligatorios y cambio de vista
        findViewById<Button>(R.id.btnSiguiente).setOnClickListener {
            try {
                if (validarCampos()) {
                    val intent = Intent(this, ContactDataActivity::class.java)
                    intent.putExtra("NOMBRE", campoNombre.text.toString())
                    intent.putExtra("APELLIDO", campoApellido.text.toString())
                    val sexo = findViewById<RadioGroup>(R.id.sexos).checkedRadioButtonId
                    if (sexo != -1) {
                        intent.putExtra("SEXO", "${findViewById<RadioButton>(sexo).text}")
                    }
                    intent.putExtra("FECHA", textofecha.text.toString())
                    if (!spinnerEducacion.selectedItem.toString().equals("Seleccione")) {
                        intent.putExtra("ESCOLARIDAD", spinnerEducacion.selectedItem.toString())
                    }
                    startActivity(intent)
                } else {
                    Log.d("TAG", "nonono, llene los datos bién y sin pereza pues")
                }
            } catch (ex: Exception) {
                Log.d("TAG", "Error por fuerita")
            }
        }

        campoNombre = findViewById(R.id.campoNombre)
        campoApellido = findViewById(R.id.campoApellido)
        textofecha = findViewById(R.id.textoFecha)

        /// DatePicker inicio
        btnFecha = findViewById(R.id.btnFecha)
        textofecha = findViewById(R.id.textoFecha)
        calendar = Calendar.getInstance()

        btnFecha.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    textofecha.setText("$dayOfMonth/${month + 1}/$year")
                },
                year, month, day
            )
            datePickerDialog.show()
        }
        /// DatePicker final

        /// Spinner inicio
        spinnerEducacion = findViewById(R.id.spinnerEducacion)
        textoSpinner = findViewById(R.id.textoSpinner)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.grados_escolaridad,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerEducacion.adapter = adapter

        spinnerEducacion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }
        /// Spinner final


    }


    private fun validarCampos(): Boolean {
        val nombre = campoNombre.text.toString()
        val apellido = campoApellido.text.toString()
        val fecha = textofecha.text.toString()

        var esValido = true


        if (nombre.isEmpty()) {
            campoNombre.setError("Este campo es obligatorio")
            esValido = false
        } else {
            campoNombre.error = null
        }

        if (apellido.isEmpty()) {
            campoApellido.setError("Este campo es obligatorio")
            esValido = false
        } else {
            campoApellido.error = null
        }

        if (fecha.isEmpty()) {
            textofecha.setError("Este campo es obligatorio")
            esValido = false
        } else {
            textofecha.error = null
        }

        return esValido
    }
}

package co.edu.udea.compumovil.gr12_20232.lab1

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.AdapterView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_data_layout)


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
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }
        /// Spinner final
    }
}

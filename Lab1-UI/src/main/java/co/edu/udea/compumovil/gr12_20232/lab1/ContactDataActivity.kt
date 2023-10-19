package co.edu.udea.compumovil.gr12_20232.lab1

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView


class ContactDataActivity : ComponentActivity() {

    private lateinit var editTextPhone: EditText
    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var textoPaises: AutoCompleteTextView
    private lateinit var textoCiudad: AutoCompleteTextView
    private lateinit var textDireccion: EditText

    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.contact_data_layout)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView2)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val nombre = intent.getStringExtra("NOMBRE").toString()
        val apellido = intent.getStringExtra("APELLIDO").toString()
        val sexo = intent.getStringExtra("SEXO")
        val fecha = intent.getStringExtra("FECHA").toString()
        val escolaridad = intent.getStringExtra("ESCOLARIDAD").toString()

        findViewById<Button>(R.id.btn_siguiente).setOnClickListener {
            try {
                if (validar()){
                    Log.d("Información Personal", "Información Personal" )
                    Log.d("NOMBRE", nombre)
                    Log.d("APELLIDO", apellido)
                    if (!sexo.isNullOrEmpty()){Log.d("SEXO", sexo.toString())}
                    Log.d("FECHA DE NACIMIENTO", fecha)
                    if (escolaridad != "Seleccione:"){Log.d("GRADO DE ESCOLARIDAD", escolaridad)}
                    imprimirDatos()
                }else{
                    Log.d("TAG", "nonono, llene los datos bién y sin pereza pues")
                }
            }catch (ex: Exception){
                Log.d("TAG", "Error por fuerita")
            }
        }



        editTextPhone = findViewById(R.id.editTextPhone)
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        textoPaises = findViewById(R.id.textoPaises)
        textoCiudad = findViewById(R.id.textoCiudad)
        textDireccion = findViewById(R.id.textDireccion)

        val paises_latam = arrayOf(
            "Bolivia", "Brasil", "Chile",
            "Colombia", "Costa Rica", "Cuba", "Ecuador", "El Salvador",
            "Guatemala", "Honduras", "México", "Nicaragua", "Panamá", "Paraguay",
            "Perú", "Puerto Rico", "República Dominicana", "Uruguay")

        val ciudades_colombia = arrayOf(
            "Bogotá", "Medellín", "Cali", "Santa Marta",
            "Barranquilla", "Villavicencio", "Bucaramanga", "Tunja"
        )

        val paises_adaptador = findViewById<AutoCompleteTextView>(R.id.textoPaises)


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, paises_latam)
        paises_adaptador.threshold = 0
        paises_adaptador.setAdapter(adapter)

        val ciudades_adaptador = findViewById<AutoCompleteTextView>(R.id.textoCiudad)
        val adapter_dos = ArrayAdapter(this, android.R.layout.simple_list_item_1, ciudades_colombia)
        ciudades_adaptador.threshold = 0
        ciudades_adaptador.setAdapter(adapter_dos)


    }

    private fun validar(): Boolean {
        val telefono = editTextPhone.text.toString()
        val correo = editTextTextEmailAddress.text.toString()
        val pais = textoPaises.text.toString().trim()

        var Valido = true


        if (telefono.isEmpty()) {
            editTextPhone.error = "Este campo es obligatorio"
            Valido = false
        }else {
            editTextPhone.error = null
        }

        if (correo.isEmpty()) {
            editTextTextEmailAddress.error = "Este campo es obligatorio"
            Valido = false
        }else if (!validarCorreo(correo)){
            editTextTextEmailAddress.error = "Este campo debe ser en formato de correo"
            Valido = false
        }
            else{
            editTextTextEmailAddress.error = null
        }

        if (pais.isEmpty()) {
            textoPaises.error = "Este campo es obligatorio"
            Valido = false
        }else {
            textoPaises.error = null
        }

        return Valido
    }

    private fun imprimirDatos(){
        Log.d("Información de Contacto", "Información de Contacto" )
        Log.d("TELEFONO", editTextPhone.text.toString())
        Log.d("CORREO", editTextTextEmailAddress.text.toString())
        Log.d("PAÍS", textoPaises.text.toString())
        if (!textoCiudad.text.isEmpty()){
        Log.d("CIUDAD", textoCiudad.text.toString())}
        if (!textDireccion.text.isEmpty()){
        Log.d("DIRECCIÓN", textDireccion.text.toString())}
    }

    fun validarCorreo(correo: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }

}


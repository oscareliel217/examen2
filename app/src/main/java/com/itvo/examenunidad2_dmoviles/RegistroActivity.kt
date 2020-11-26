package com.itvo.examenunidad2_dmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText

/**
 * Asignatura: Dispositivos Moviles 2
 * Docente: I.S.C. Kevin David Molina Gomez
 * Archivo: RegistroActivity.kt - Actividad que registra a los usuarios.
 * TODO: NOMBRE DEL ALUMNO: "OSCAR ELIEL FRANCO JIMENEZ"
 */
class RegistroActivity : AppCompatActivity() {

    /**
     * Se declaran las view (Vistas), se declaran en esta parte por que se podran usar desde cualquier metodo, sin tener que declararlas de nuevo
     * El lateint sirve para que no tengamos que inicializar las vistas aqui, si no hasta cuando queramos, que seria en el metodo inicializarVistas.
     * */
    private lateinit var etNombre: EditText
    private lateinit var etApellido:EditText
    private lateinit var etCorreo: EditText
    private lateinit var etContrasena:EditText
    private lateinit var etRepetirContrasena:EditText
    private lateinit var btnRegistrar:Button
    private val minimaLongitud = 6

    /**
     *  Metodo principal de la actividad en android, aqui se mandan a llamar los demas metodos para separar el codigo.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        inicializarVistas()
        registrarUsuario()
    }

    /**
     * Metodo que inicializa las view (Vistas), que se declararon en el activity_inicio_sesion.xml
     * */
    private fun inicializarVistas() {

        etNombre = findViewById(R.id.et_primer_nombre)
        etApellido = findViewById(R.id.et_apellido)
        etCorreo = findViewById(R.id.et_correo)
        etContrasena = findViewById(R.id.et_contrasena)
        etRepetirContrasena = findViewById(R.id.et_repetir_contrasena)
        btnRegistrar = findViewById(R.id.btn_registrar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    /**
     * Metodo que valida si la informacion que ingresamos es valida.
     * */
    private fun validarDatos(): Boolean {
        if (etNombre.text.toString() == "") {
            etNombre.error = "Please Enter First Name"
            return false
        }
        if (etApellido.text.toString() == "") {
            etApellido.error = "Please Enter Last Name"
            return false
        }
        if (etCorreo.text.toString() == "") {
            etCorreo.error = "Please Enter Email"
            return false
        }
        if (etContrasena.text.toString() == "") {
            etContrasena.error = "Please Enter Password"
            return false
        }
        if (etRepetirContrasena.text.toString() == "") {
            etRepetirContrasena.error = "Please Enter Repeat Password"
            return false
        }

        // checking the proper email format
        if (!validarCorreo(etCorreo.text.toString())) {
            etCorreo.error = "Please Enter Valid Email"
            return false
        }

        // checking minimum password Length
        if (etContrasena.text.length < minimaLongitud) {
            etContrasena.error = "Password Length must be more than " + minimaLongitud + "characters"
            return false
        }

        // Checking if repeat password is same
        if (etContrasena.text.toString() != etRepetirContrasena.text.toString()) {
            etRepetirContrasena.error = "Password does not match"
            return false
        }
        return true
    }


    /**
     * Metodo que valida que el correo contenga el simbolo @ y tenga la estructura de un correo.
     * */
    private fun validarCorreo(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Metodo que se activa cuando presionamos el boton Registrar Usuario
     * */
    fun registrarUsuario() {
        btnRegistrar.setOnClickListener {
            if (validarDatos()) {
                val intent = Intent(this, PerfilActivity::class.java)
                //TODO: ESCRIBIR CODIGO PARA IR A LA ACTIVITY PerfilActivity mandando los datos que estan dentro de los EditText por medio de Intents.
                var nom = etNombre.text.toString()
                var ape = etApellido.text.toString()
                var corr = etCorreo.text.toString()
                intent.putExtra("nombre", nom)
                intent.putExtra("apellido", ape)
                intent.putExtra("correo", corr)
                startActivity(intent)
            }
        }

    }
}
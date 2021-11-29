package pe.edu.ulima.pm.asesoriasulima

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.asesoriasulima.model.LoginManager


var radioButtonAlumno: RadioButton? = null
var radioButtonProfesor: RadioButton? = null
var radioButtonGroup: RadioGroup? = null
var rol: String? = null

class RegistroActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_registro)

        val btnRegistrarseForm = findViewById<Button>(R.id.btnRegistrarseForm)

        radioButtonGroup = findViewById(R.id.containerButtonGroup)
        radioButtonAlumno = findViewById(R.id.radioAlumno)
        radioButtonProfesor = findViewById(R.id.radioProfesor)
        radioButtonGroup!!.setOnCheckedChangeListener(this)
        btnRegistrarseForm.setOnClickListener {


            val codigoform = findViewById<EditText>(R.id.etCodigoForm)
            val nombreform = findViewById<EditText>(R.id.etNombreForm)
            val passwordform = findViewById<EditText>(R.id.etPasswordForm)

            if (codigoform.text.toString() != "" && nombreform.text.toString() != "" && passwordform.text.toString() != "" && rol!=null) {
                LoginManager.instance.registrarUsuarioEnFirebase(
                    codigoform.text.toString(),
                    nombreform.text.toString(),
                    passwordform.text.toString(),
                    rol!!,
                    {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        codigoform.setText("")
                        nombreform.setText("")
                        passwordform.setText("")
                        rol=null
                        radioButtonGroup!!.clearCheck()
                        val intent: Intent = Intent()
                        intent.setClass(this, LoginActivity::class.java) //pasamos next activity
                        startActivity(intent)
                    },
                    {
                        Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()

                    })
            } else {
                Toast.makeText(this, "Campos obligatorios", Toast.LENGTH_SHORT).show()

            }

        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, idRadio: Int) {
        when (idRadio) {
            radioButtonAlumno?.id -> rol = "Alumno"
            radioButtonProfesor?.id -> rol = "Profesor"
        }
    }
}
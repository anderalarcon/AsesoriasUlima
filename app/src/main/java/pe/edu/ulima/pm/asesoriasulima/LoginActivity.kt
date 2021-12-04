package pe.edu.ulima.pm.asesoriasulima

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import pe.edu.ulima.pm.asesoriasulima.model.LoginManager
import java.io.FileNotFoundException
import java.util.*

data class LoginInfo(val username: String, val LoginDate: Long)
class LoginActivity : AppCompatActivity() {
    var pantallaFragment = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)

        var existeYesAlumno = false
        var existeYesProfe = false


        var idLongGlobal : Long = 0

     /* if (isLoguedInterno()) {//pasar directamente al main activity
            val username = getLoginUsernameInterno()
            val intent: Intent = Intent()
            intent.setClass(this, MainActivity::class.java) //pasamos next activity
            startActivity(intent)*/

   /* if (isLoguedInterno()) {//pasar directamente al main activity
          println("YA SE HABIA LOGUEADO")
            val codigo= getLoginCodigoInterno()
            changeToMainActivityProfesor(codigo)

        }*/
    /*hola*/
        btnIngresar.setOnClickListener {
            if (findViewById<EditText>(R.id.etcodigoLogin).text.toString() != "" && findViewById<EditText>(
                    R.id.etpasswordLogin
                ).text.toString() != ""
            ) {
                LoginManager.instance.findUsers({ res ->
                    val intent: Intent = Intent()
                    for (i in res) {
                        if (i.codigo == findViewById<EditText>(R.id.etcodigoLogin).text.toString() && i.password == findViewById<EditText>(
                                R.id.etpasswordLogin
                            ).text.toString() && i.rol == "Alumno"
                        ) {
                            existeYesAlumno = true
                            idLongGlobal = i.id
                        } else if (i.codigo == findViewById<EditText>(R.id.etcodigoLogin).text.toString() && i.password == findViewById<EditText>(
                                R.id.etpasswordLogin
                            ).text.toString() && i.rol == "Profesor"
                        ) {
                            existeYesProfe = true
                            idLongGlobal = i.id
                        }
                    }
                    if (existeYesAlumno) {
                        almacenarAfterLogin(findViewById<EditText>(R.id.etcodigoLogin).text.toString())
                        pantallaFragment = 1
                        val bundle: Bundle = Bundle()//Almacenamos data
                        bundle.putInt("pantallaFragment", pantallaFragment)
                        bundle.putString("codigo", findViewById<EditText>(R.id.etcodigoLogin).text.toString())
                        bundle.putLong("id",idLongGlobal)
                        intent.putExtra("data", bundle)
                        intent.setClass(this, MainActivity::class.java) //pasamos next activity
                        startActivity(intent)
                        Toast.makeText(this, "Bienvenido Alumno", Toast.LENGTH_SHORT)
                            .show()
                    } else if (existeYesProfe) {
                        pantallaFragment = 2
                        val bundle: Bundle = Bundle()//Almacenamos data
                        almacenarAfterLogin(findViewById<EditText>(R.id.etcodigoLogin).text.toString())
                        bundle.putInt("pantallaFragment", pantallaFragment)
                        bundle.putString("codigo", findViewById<EditText>(R.id.etcodigoLogin).text.toString())
                        bundle.putLong("id",idLongGlobal)
                        intent.putExtra("data", bundle)
                        intent.setClass(this, MainActivityProfesor::class.java) //pasamos next activity
                        startActivity(intent)
                        Toast.makeText(this, "Bienvenido Profe", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Error en credenciales", Toast.LENGTH_SHORT).show()
                    }

                }, { error ->
                    Toast.makeText(this, "Error:" + error, Toast.LENGTH_SHORT).show()
                })

                existeYesAlumno = false
                existeYesProfe = false
            } else {
                Toast.makeText(this, "Campos obligatorios", Toast.LENGTH_SHORT).show()
            }


        }

        btnRegistrarse.setOnClickListener {
            val intent: Intent = Intent()
            intent.setClass(this, RegistroActivity::class.java) //pasamos next activity
            startActivity(intent)
        }

    }

    private fun changeToMainActivityProfesor(codigo:String) {
        val intent: Intent = Intent()
        //siempre que nos pide un context , poner this  y a donde quiero ir
        intent.setClass(this, MainActivityProfesor::class.java)

        //Tipo hashmap,diccionario. Para guardar datos y poderlo usar en el sgte activity
        val bundle: Bundle = Bundle()
        bundle.putString("codigo", codigo)
        intent.putExtra("data", bundle)

        startActivity(intent)
    }

    fun almacenarAfterLogin(codigo: String) {
        val gson = Gson()
        val logininfo = LoginInfo(codigo, Date().time)
        //guardamos como archivo
        openFileOutput("Login_info.json", Context.MODE_PRIVATE).use {
            it.write(gson.toJson(logininfo).toByteArray(Charsets.UTF_8))
        }
    }


    private fun isLoguedInterno(): Boolean {
        var cadena: String = ""

        try {
            openFileInput("Login_info.json").use {
                val byteArray = it.readBytes()
                cadena = String(byteArray)
                Log.i("LoginActivity", cadena)
            }
        } catch (fnfe: FileNotFoundException) {
            return false
        }
        return true

    }

    fun getLoginCodigoInterno(): String {
        var cadena: String = ""

        try {
            openFileInput("Login_info.json").use {
                val byteArray = it.readBytes()
                cadena = String(byteArray)

            }
        } catch (fnfe: FileNotFoundException) {
            return ""
        }
        val logininfo = Gson().fromJson(cadena, LoginInfo::class.java)
        return logininfo.username

    }
}
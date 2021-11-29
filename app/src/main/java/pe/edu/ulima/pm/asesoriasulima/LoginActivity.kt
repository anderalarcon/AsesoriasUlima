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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)

        var cheq = false

        if (isLoguedInterno()) {//pasar directamente al main activity
            val username = getLoginUsernameInterno()
            val intent: Intent = Intent()
            intent.setClass(this, MainActivity::class.java) //pasamos next activity
            startActivity(intent)
        }

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
                            ).text.toString()
                        ) {
                            cheq = true
                        }
                    }
                    if (cheq) {
                        almacenarAfterLogin(findViewById<EditText>(R.id.etcodigoLogin).text.toString())
                        intent.setClass(this, MainActivity::class.java) //pasamos next activity
                        startActivity(intent)
                        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Error en credenciales", Toast.LENGTH_SHORT).show()
                    }

                }, { error ->
                    Toast.makeText(this, "Error:" + error, Toast.LENGTH_SHORT).show()
                })

                cheq = false
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

    fun almacenarAfterLogin(username: String) {
        val gson = Gson()
        val logininfo = LoginInfo(username, Date().time)
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
        //val loginInfo=Gson().fromJson<LoginInfo>(cadena,LoginInfo::class.java)
        //de json a objeto

    }

    fun getLoginUsernameInterno(): String {
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
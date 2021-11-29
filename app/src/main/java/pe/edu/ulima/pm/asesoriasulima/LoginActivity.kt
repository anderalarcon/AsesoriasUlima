package pe.edu.ulima.pm.asesoriasulima

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.asesoriasulima.model.LoginManager

class LoginActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)
        val btnIngresar=findViewById<Button>(R.id.btnIngresar)
        val btnRegistrarse=findViewById<Button>(R.id.btnRegistrarse)


        btnIngresar.setOnClickListener{
            val intent: Intent = Intent()
            Toast.makeText(this, "Verificar si existe", Toast.LENGTH_SHORT).show()
            intent.setClass(this, MainActivity::class.java) //pasamos next activity
            startActivity(intent)

        }

        btnRegistrarse.setOnClickListener{
            val intent: Intent = Intent()
            intent.setClass(this, RegistroActivity::class.java) //pasamos next activity
            startActivity(intent)
        }

    }
}
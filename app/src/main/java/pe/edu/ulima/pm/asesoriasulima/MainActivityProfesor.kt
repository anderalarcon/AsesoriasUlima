package pe.edu.ulima.pm.asesoriasulima

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.asesoriasulima.fragments.AsesoriasAlumnoFragment
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.MisAsesoriasFragment

class MainActivityProfesor:AppCompatActivity() {
    private val fragments = mutableListOf<Fragment>()
    private lateinit var dlaMain: DrawerLayout
    var pantallaFragment: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        pantallaFragment = intent.getBundleExtra("data")?.getInt("pantallaFragment")!!
        println("codigo" + intent.getBundleExtra("data")?.getString("codigo")!!)
        println("pantalla: " + pantallaFragment)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profesor)


        println("Profesor")
        fragments.add(MisAsesoriasFragment())
    }
}
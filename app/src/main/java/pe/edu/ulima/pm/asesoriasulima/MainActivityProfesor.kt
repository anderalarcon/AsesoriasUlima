package pe.edu.ulima.pm.asesoriasulima

import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import pe.edu.ulima.pm.asesoriasulima.fragments.AsesoriasAlumnoFragment
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.MiCuentaFragment
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.MisAsesoriasFragment
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.RegistrarAsesoriaFragment

class MainActivityProfesor:AppCompatActivity(),MisAsesoriasFragment.interfaceAsesoriasProfesor {
    private val fragments = mutableListOf<Fragment>()
    private lateinit var dlaMain: DrawerLayout
    var pantallaFragment: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        pantallaFragment = intent.getBundleExtra("data")?.getInt("pantallaFragment")!!

        var codigo:String=intent.getBundleExtra("data")?.getString("codigo")!!

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main_profesor)



        fragments.add(MisAsesoriasFragment(codigo))
        fragments.add(MiCuentaFragment())
        fragments.add(RegistrarAsesoriaFragment(codigo))


        // Configurando NavigationView
        val nviMain = findViewById<NavigationView>(R.id.nviMainProfesor)
        dlaMain = findViewById<DrawerLayout>(R.id.dlaMainProfesor)

        nviMain.setNavigationItemSelectedListener { menuItem: MenuItem ->
            if (menuItem.itemId == R.id.menProfesorAsesorias) {
                changeToProfesorAsesoriasFragment()
            } else if (menuItem.itemId == R.id.menCuentaProfesor) {
                changeToMicuentaProfesorFragment()
            } else if (menuItem.itemId == R.id.menProfesorSalir) {
                Salir()
            }

            menuItem.isChecked = true
            dlaMain.closeDrawers()
            true
        }



        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContentProfesor, fragments[0])
        ft.commit()

    }

    private fun Salir() {
        Toast.makeText(this,"Implementar Salir",Toast.LENGTH_SHORT).show()
    }

    private fun changeToProfesorAsesoriasFragment() {
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContentProfesor, fragment)
        ft.commit()
    }

    private fun changeToMicuentaProfesorFragment() {
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContentProfesor, fragment)
        ft.commit()
    }

    override fun ChangeToRegistrarNuevaAsesoria() {
        val fragment = fragments[2]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContentProfesor, fragment)
        ft.commit()
    }


}
package pe.edu.ulima.pm.asesoriasulima

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import pe.edu.ulima.pm.asesoriasulima.fragments.AsesoriasAlumnoFragment
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.DetalleAsesoriaProfesorFragment
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.MiCuentaFragment
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.MisAsesoriasFragment
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.RegistrarAsesoriaFragment
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias
import pe.edu.ulima.pm.asesoriasulima.model.CuentaManager
import java.io.FileNotFoundException

class MainActivityProfesor:AppCompatActivity(),MisAsesoriasFragment.interfaceAsesoriasProfesor,MiCuentaFragment.interfaceCuentaProfe {
    private val fragments = mutableListOf<Fragment>()
    private lateinit var dlaMain: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {

        var codigo=getLoginCodigoInterno()
        println("ACAAAA:"+codigo)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_profesor)



        fragments.add(MisAsesoriasFragment(codigo.username))
        fragments.add(MiCuentaFragment())
        fragments.add(RegistrarAsesoriaFragment(codigo.username))


        //Configurando hamburguer
        val actionBar=supportActionBar

        actionBar?.setHomeAsUpIndicator(DrawerArrowDrawable(this))
        actionBar?.setDisplayHomeAsUpEnabled(true)

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

        try {

            deleteFile("Login_infoProfe.json")
            val intent: Intent = Intent()
            intent.setClass(this, LoginActivity::class.java) //pasamos next activity
            startActivity(intent)
        }catch (fnfe: FileNotFoundException){
            println("ERROR")
        }
    }

    private fun changeToProfesorAsesoriasFragment() {
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContentProfesor, fragment)
        ft.commit()
    }

    fun getLoginCodigoInterno():LoginInfo {
        var cadena: String = ""

        try {
            openFileInput("Login_infoProfe.json").use {
                val byteArray = it.readBytes()
                cadena = String(byteArray)

            }


        } catch (fnfe: FileNotFoundException) {
            println("ERROR")
        }
        val logininfo = Gson().fromJson(cadena, LoginInfo::class.java)
        return logininfo

    }

    private fun changeToMicuentaProfesorFragment() {
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContentProfesor, fragment)
        ft.commit()
    }

    private fun changeDetalleAsesoriaProfesor(asesoria: Asesorias) {
        val fragment = DetalleAsesoriaProfesorFragment(asesoria)
        val ft = supportFragmentManager.beginTransaction()
        //remplazar un nuevo fragment
        ft.replace(R.id.flaContentProfesor, fragment)
        ft.commit()
    }

    override fun ChangeToRegistrarNuevaAsesoria() {
        val fragment = fragments[2]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContentProfesor, fragment)
        ft.commit()
    }

    override fun onSelectCardAsesoriaProfesor(cardAsesoria: Asesorias) {
       println(cardAsesoria)
        changeDetalleAsesoriaProfesor(cardAsesoria)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            dlaMain.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }


    override fun UpdateCuentaProfe(NewNombre: String, NewPassword: String) {
        if(NewNombre.equals("") && NewPassword.equals("")){
            Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show()
        }else{

            CuentaManager.instance.updateUsuarioProfe(getLoginCodigoInterno().id, NewNombre, NewPassword)
            Toast.makeText(this, "Datos Actualizados", Toast.LENGTH_SHORT).show()

        }
    }


}
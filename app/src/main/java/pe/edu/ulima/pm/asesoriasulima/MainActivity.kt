package pe.edu.ulima.pm.asesoriasulima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import pe.edu.ulima.pm.asesoriasulima.fragments.*
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.MisAsesoriasFragment
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasAlumno
import pe.edu.ulima.pm.asesoriasulima.model.CuentaManager
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity(),
    AsesoriasAlumnoFragment.interfaceAsesoriasALumnos,
    DetalleAsesoriaAlumnoFragment.interfaceDetalleALumnos,
    CrearAsesoriaAlumnoFragment.interfaceRegistroALumnos,
    CuentaFragment.interfaceCuentaAlumnos{

    private val fragments = mutableListOf<Fragment>()
    private lateinit var dlaMain: DrawerLayout
    var pantallaFragment: Int = 0
    var IdDocumentoUser: Long = 0
    //var idAsesorias : Long = 0
    private lateinit var AsesoriaGlobal : Asesorias

    override fun onCreate(savedInstanceState: Bundle?) {
        AsesoriaGlobal = Asesorias(1,"","","","","","")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            fragments.add(AsesoriasAlumnoFragment())
            fragments.add(RegistroAlumnosFragment())
            fragments.add(CuentaFragment())
            fragments.add(DetalleAsesoriaAlumnoFragment(AsesoriaGlobal))
            fragments.add(CrearAsesoriaAlumnoFragment(AsesoriaGlobal,""))

            // Configurando NavigationView
            val nviMain = findViewById<NavigationView>(R.id.nviMainAlumnos)
            dlaMain = findViewById<DrawerLayout>(R.id.dlaMainAlumnos)

            nviMain.setNavigationItemSelectedListener { menuItem: MenuItem ->
                if (menuItem.itemId == R.id.menAlumnosAsesorias) {
                    changeAlumnosAsesoriasFragment()
                } else if (menuItem.itemId == R.id.menAlumnosRegistros) {
                    changeAlumnosRegistrosFragment()
                } else if (menuItem.itemId == R.id.menCuenta) {
                    changeCuentaFragment()
                }else if(menuItem.itemId == R.id.menAlumnosSalir){
                    SalirAlumno()
                    //falta borrar JSON interno
                }

                menuItem.isChecked = true
                dlaMain.closeDrawers()
                true
            }

            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.flaContent, fragments[0])
            ft.commit()



    }

    private fun SalirAlumno(){
        try {

            deleteFile("Login_infoAlumno.json")
            finish()
            Toast.makeText(this, "Sesión finalizada", Toast.LENGTH_SHORT).show()
            /*
            val intent: Intent = Intent()
            intent.setClass(this, LoginActivity::class.java) //pasamos next activity
            startActivity(intent)*/
        }catch (fnfe: FileNotFoundException){
            println(fnfe)
        }
    }

    private fun changeAlumnosAsesoriasFragment() {
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment)
        ft.commit()
    }

    private fun changeAlumnosRegistrosFragment() {
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment)
        ft.commit()
    }

    private fun changeCuentaFragment() {
        val fragment = fragments[2]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment)
        ft.commit()
    }

    private fun changeDetalleAsesoriaAlumnoFragment() {
        val fragment = fragments[3]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment)
        ft.commit()
    }

    private fun changeDetalleAsesoriaAlumnoFragmentCONINFO(Asesoria : Asesorias) {
        AsesoriaGlobal = Asesoria
        val fragment = DetalleAsesoriaAlumnoFragment(Asesoria)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }

    private fun changeCrearAsesoriaAlumnoFragment(Asesoria: Asesorias) {
        val fragment = CrearAsesoriaAlumnoFragment(Asesoria, getLoginCodigoInternoAlumno().username)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment)
        ft.commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            dlaMain.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)

    }

    override fun ChangeVerDetalle(Asesoria: Asesorias) {
        AsesoriaGlobal = Asesoria
        println(AsesoriaGlobal)
        changeDetalleAsesoriaAlumnoFragmentCONINFO(Asesoria)
    }

    override fun ChangeRegistrarAsesoriaAlumno(Asesoria: Asesorias) {
        AsesoriaGlobal = Asesoria
        changeCrearAsesoriaAlumnoFragment(AsesoriaGlobal)
    }

    override fun RegresarAsesorias() {
        changeAlumnosAsesoriasFragment()
    }

    override fun CopiarAsesorias() {
        Toast.makeText(this, "Copiado en el portapapeles", Toast.LENGTH_SHORT).show()
    }

    override fun ConfirmarAsesoria() {
        //changeDetalleAsesoriaAlumnoFragment()
        changeDetalleAsesoriaAlumnoFragmentCONINFO(AsesoriaGlobal)
        Toast.makeText(this, "Asesoria registrada correctamente", Toast.LENGTH_SHORT).show()
    }

    override fun RegresaDetalle() {
        //changeDetalleAsesoriaAlumnoFragment()
        changeDetalleAsesoriaAlumnoFragmentCONINFO(AsesoriaGlobal)
    }

    override fun UpdateCuentaAlumno(NewNombre: String, NewPassword: String) {
        if(NewNombre.equals("") && NewPassword.equals("")){
            Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show()
        }else{
            CuentaManager.instance.updateUsuarioAlumno(getLoginCodigoInternoAlumno().id, NewNombre, NewPassword)
            Toast.makeText(this, "Datos Actualizados", Toast.LENGTH_SHORT).show()
        }


    }

    fun getLoginCodigoInternoAlumno():LoginInfo {
        var cadena: String = ""

        try {
            openFileInput("Login_infoAlumno.json").use {
                val byteArray = it.readBytes()
                cadena = String(byteArray)

            }


        } catch (fnfe: FileNotFoundException) {
            println("ERROR")
        }
        val logininfo = Gson().fromJson(cadena, LoginInfo::class.java)
        return logininfo

    }


}
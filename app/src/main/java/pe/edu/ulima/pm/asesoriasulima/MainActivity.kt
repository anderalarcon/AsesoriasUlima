package pe.edu.ulima.pm.asesoriasulima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import pe.edu.ulima.pm.asesoriasulima.fragments.*
import pe.edu.ulima.pm.asesoriasulima.fragments.profesor.MisAsesoriasFragment
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasAlumno
import pe.edu.ulima.pm.asesoriasulima.model.CuentaManager

class MainActivity : AppCompatActivity(),
    AsesoriasAlumnoFragment.interfaceAsesoriasALumnos,
    DetalleAsesoriaAlumnoFragment.interfaceDetalleALumnos,
    CrearAsesoriaAlumnoFragment.interfaceRegistroALumnos,
    CuentaFragment.interfaceCuentaAlumnos{

    private val fragments = mutableListOf<Fragment>()
    private lateinit var dlaMain: DrawerLayout
    var pantallaFragment: Int = 0
    var IdDocumentoUser: Long = 0
    private lateinit var AsesoriaGlobal : AsesoriasAlumno

    override fun onCreate(savedInstanceState: Bundle?) {
        AsesoriaGlobal = AsesoriasAlumno(1,"","","","")
        pantallaFragment = intent.getBundleExtra("data")?.getInt("pantallaFragment")!!
       println("codigo" + intent.getBundleExtra("data")?.getString("codigo")!!)
        println("pantalla: " + pantallaFragment)
        println("IDDocumento: " + intent.getBundleExtra("data")?.getLong("id")!!)
        IdDocumentoUser = intent.getBundleExtra("data")?.getLong("id")!!

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (pantallaFragment == 1) {
            println("Alumno")
            fragments.add(AsesoriasAlumnoFragment())
            fragments.add(RegistroAlumnosFragment())
            fragments.add(CuentaFragment())
            fragments.add(DetalleAsesoriaAlumnoFragment(AsesoriaGlobal))
            fragments.add(CrearAsesoriaAlumnoFragment())

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
                    finish()
                    Toast.makeText(this, "Sesión finalizada", Toast.LENGTH_SHORT).show()
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

    private fun changeDetalleAsesoriaAlumnoFragmentCONINFO(Asesoria : AsesoriasAlumno) {
        AsesoriaGlobal = Asesoria
        val fragment = DetalleAsesoriaAlumnoFragment(Asesoria)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }

    private fun changeCrearAsesoriaAlumnoFragment() {
        val fragment = fragments[4]
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

    override fun ChangeVerDetalle(Asesoria: AsesoriasAlumno) {
        AsesoriaGlobal = Asesoria
        println(AsesoriaGlobal)
        changeDetalleAsesoriaAlumnoFragmentCONINFO(Asesoria)
    }

    override fun ChangeRegistrarAsesoriaAlumno() {
        changeCrearAsesoriaAlumnoFragment()
    }

    override fun RegresarAsesorias() {
        changeAlumnosAsesoriasFragment()
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
            CuentaManager.instance.updateUsuarioAlumno(IdDocumentoUser, NewNombre, NewPassword)
            Toast.makeText(this, "Datos Actualizados", Toast.LENGTH_SHORT).show()
        }


    }


}
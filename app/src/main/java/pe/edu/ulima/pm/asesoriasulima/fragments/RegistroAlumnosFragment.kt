package pe.edu.ulima.pm.asesoriasulima.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.adapter.RegistrosAlumnosAdapter
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasAlumno
import pe.edu.ulima.pm.asesoriasulima.model.RegistrosAlumnos

class RegistroAlumnosFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registros_alumno, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listRegistros = arrayListOf<RegistrosAlumnos>()
        listRegistros.add((RegistrosAlumnos(1,"Programación Móvil","Dudas del trabajo","801","Hernan Quintana","30/11/2021","1","enlace 1")))
        listRegistros.add((RegistrosAlumnos(2,"Ingeniería de SW I","Tesis","801","Nina Hanco","30/11/2021","4","enlace 2")))
        listRegistros.add((RegistrosAlumnos(2,"Seminario de Investigación I","Tesis - requisitos","902","Rosario","02/12/2021","4","enlace 3")))

        val rviRegistros = view.findViewById<RecyclerView>(R.id.rviRegistrosAlumnos)
        rviRegistros.adapter = RegistrosAlumnosAdapter(
            listRegistros,
            this,
            {  }
        )

    }
}
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
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasManager
import pe.edu.ulima.pm.asesoriasulima.model.RegistrosAlumnos

class RegistroAlumnosFragment(val codigoAlumno: String): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registros_alumno, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rviRegistros = view.findViewById<RecyclerView>(R.id.rviRegistrosAlumnos)
        AsesoriasManager.instance.GetRegistrosAlumnoFirebase(codigoAlumno, { res ->
            print("LISTAA REGISTROS:" + res)

            rviRegistros.adapter = RegistrosAlumnosAdapter(
                res,
                this
            ) { }

        }, { error ->
            println(error)
        }
        )
    }
}
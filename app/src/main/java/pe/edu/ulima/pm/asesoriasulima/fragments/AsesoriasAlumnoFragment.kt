package pe.edu.ulima.pm.asesoriasulima.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.adapter.AsesoriasAlumnosAdapter
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasAlumno

class AsesoriasAlumnoFragment: Fragment() {

    interface interfaceAsesoriasALumnos{
        fun ChangeVerDetalle(Asesorias : AsesoriasAlumno)
    }

    private var listener: interfaceAsesoriasALumnos? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceAsesoriasALumnos
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_asesorias_alumno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listaAsesorias = arrayListOf<AsesoriasAlumno>()
        listaAsesorias.add(AsesoriasAlumno(1,"Programación móvil","801","Hernan Quintana","lunes 9 - 10"))
        listaAsesorias.add(AsesoriasAlumno(2,"Ingenieria de SW II","801","Nina Hanco","miercoles 9 - 10"))
        /*listaAsesorias.add(AsesoriasAlumno(2,"Ingenieria de SW II","801","Nina Hanco","miercoles 9 - 10"))
        listaAsesorias.add(AsesoriasAlumno(2,"Ingenieria de SW II","801","Nina Hanco","miercoles 9 - 10"))
        listaAsesorias.add(AsesoriasAlumno(2,"Ingenieria de SW II","801","Nina Hanco","miercoles 9 - 10"))*/


        val rviAsesorias = view.findViewById<RecyclerView>(R.id.rviAsesoriasAlumnos)
        rviAsesorias.adapter = AsesoriasAlumnosAdapter(
            listaAsesorias,
            this
        ){AsesoriasAlumno : AsesoriasAlumno ->
            println("xd")
            listener?.ChangeVerDetalle(AsesoriasAlumno)
        }

    }
}
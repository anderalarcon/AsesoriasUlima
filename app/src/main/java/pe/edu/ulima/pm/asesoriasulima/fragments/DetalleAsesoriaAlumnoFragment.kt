package pe.edu.ulima.pm.asesoriasulima.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.asesoriasulima.R

class DetalleAsesoriaAlumnoFragment:Fragment() {

    interface interfaceDetalleALumnos{
        fun ChangeRegistrarAsesoriaAlumno()
        fun RegresarAsesorias()
    }

    private var listener: interfaceDetalleALumnos? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceDetalleALumnos
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalle_asesoria_alumno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val butRegistrar = view.findViewById<Button>(R.id.butRegistrarAsesoria)
        val butRegresarAsesorias = view.findViewById<Button>(R.id.butVolverAsesorias)

        butRegistrar.setOnClickListener{
            listener?.ChangeRegistrarAsesoriaAlumno()
        }

        butRegresarAsesorias.setOnClickListener{
            listener?.RegresarAsesorias()
        }
    }
}
package pe.edu.ulima.pm.asesoriasulima.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.asesoriasulima.R

class CrearAsesoriaAlumnoFragment:Fragment() {
    interface interfaceRegistroALumnos{
        fun ConfirmarAsesoria()
        fun RegresaDetalle()
    }

    private var listener: interfaceRegistroALumnos? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceRegistroALumnos
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registro_asesoria_alumno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val butConfirmar = view.findViewById<Button>(R.id.butConfirmarAsesoriaAlumno)
        val butRegresarDetalles = view.findViewById<Button>(R.id.butRegresarDetalleAsesoria)

        butConfirmar.setOnClickListener{
            listener?.ConfirmarAsesoria()
        }

        butRegresarDetalles.setOnClickListener{
            listener?.RegresaDetalle()
        }
    }

}
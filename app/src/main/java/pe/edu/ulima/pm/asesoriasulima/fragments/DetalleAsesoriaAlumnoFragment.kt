package pe.edu.ulima.pm.asesoriasulima.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.adapter.AsesoriasRegistradosAdapter
import pe.edu.ulima.pm.asesoriasulima.adapter.RegistrosAlumnosAdapter
import pe.edu.ulima.pm.asesoriasulima.model.*

class DetalleAsesoriaAlumnoFragment (val Asesoria : Asesorias):Fragment() {

    interface interfaceDetalleALumnos{
        fun ChangeRegistrarAsesoriaAlumno(Asesoria: Asesorias)
        fun RegresarAsesorias()
        fun CopiarAsesorias()
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

        view.findViewById<TextView>(R.id.tviCursoAsesoriaAlumnoDETALLE).setText(Asesoria.nombreCurso)
        view.findViewById<TextView>(R.id.tviSeccionAsesoriaAlumnoDETALLE).setText("Sección: ${Asesoria.seccion}")
        view.findViewById<TextView>(R.id.tviProfesorAsesoriaAlumnoDETALLE).setText("Profesor: ${Asesoria.codigo_profe}")
        view.findViewById<TextView>(R.id.tviListaAsesoriaAlumnoDETALLE).setText("Asesoria: " + Asesoria.dia  + ", " + Asesoria.horario )

      val rviListaRegistrados = view.findViewById<RecyclerView>(R.id.rviListaRegistrados)
        AsesoriasManager.instance.getRegistroProfe(
            Asesoria.id.toString(),
            {res  ->

            rviListaRegistrados.adapter = AsesoriasRegistradosAdapter(
                res.asistente,
                this
            ){}
        },{ error ->
            //Log.e("Product")
            println(error)
        }

        )

        val butRegistrar = view.findViewById<Button>(R.id.butRegistrarAsesoria)
        val butRegresarAsesorias = view.findViewById<Button>(R.id.butVolverAsesorias)
        val butEnlaceAsesoriaAlumnoDETALLE = view.findViewById<Button>(R.id.butEnlaceAsesoriaAlumnoDETALLE)

        butEnlaceAsesoriaAlumnoDETALLE.setOnClickListener {
            var clipboard : ClipboardManager = getActivity()?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val strEnlace = Asesoria.url.toString()
            val clip = ClipData.newPlainText("Copiado en el portapapeles", strEnlace)
            clipboard.setPrimaryClip(clip)
            listener?.CopiarAsesorias()
        }


        butRegistrar.setOnClickListener{
            listener?.ChangeRegistrarAsesoriaAlumno(Asesoria)

        }

        butRegresarAsesorias.setOnClickListener{
            listener?.RegresarAsesorias()
        }
    }
}
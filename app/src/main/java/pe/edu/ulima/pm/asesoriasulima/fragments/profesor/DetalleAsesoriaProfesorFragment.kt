package pe.edu.ulima.pm.asesoriasulima.fragments.profesor

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias

class DetalleAsesoriaProfesorFragment( val asesoria:Asesorias):Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalle_mis_asesorias_profesor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linktextvie:TextView



        view.findViewById<TextView>(R.id.tviEnlanceAZOOM).setText("URL: "+asesoria.url)
        view.findViewById<TextView>(R.id.tvinombreCursoDetalleProfesor).setText(asesoria.nombreCurso)
        view.findViewById<TextView>(R.id.tviSeccionDetaleProfesor).setText("Seccion: "+asesoria.seccion)

    }
}
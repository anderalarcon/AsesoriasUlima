package pe.edu.ulima.pm.asesoriasulima.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasManager
import pe.edu.ulima.pm.asesoriasulima.model.asistente

class CrearAsesoriaAlumnoFragment(val Asesoriaxd: Asesorias, val codigoAlumno: String):Fragment() {
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

        println("CODIGO ASESORIA: " + Asesoriaxd.id.toString())
        println("CODIGO ALUMNO : " + codigoAlumno)



        val butConfirmar = view.findViewById<Button>(R.id.butConfirmarAsesoriaAlumno)
        val butRegresarDetalles = view.findViewById<Button>(R.id.butRegresarDetalleAsesoria)
        val eteMotivo = view.findViewById<EditText>(R.id.eteMotivoAsesoria)


        butConfirmar.setOnClickListener{
            if(view.findViewById<EditText>(R.id.eteMotivoAsesoria).text.toString() != ""){
                AsesoriasManager.instance.RegistrarAlumnoEnAsesoria(Asesoriaxd.id!!.toString(), asistente(codigoAlumno,eteMotivo.text.toString()) /*codigoAlumno,eteMotivo.text.toString()*/)
                listener?.ConfirmarAsesoria()
            }else{
                val myToast: Toast = Toast.makeText(requireContext(), "Ingrese el motivo", Toast.LENGTH_LONG)
                myToast.show()
            }
        }

        butRegresarDetalles.setOnClickListener{
            listener?.RegresaDetalle()
        }
    }

}
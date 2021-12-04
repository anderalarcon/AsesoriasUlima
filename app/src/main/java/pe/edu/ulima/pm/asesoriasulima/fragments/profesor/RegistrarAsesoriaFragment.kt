package pe.edu.ulima.pm.asesoriasulima.fragments.profesor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.LoginActivity
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.adapter.adapterProfesor.MisAsesoriasProfesorAdapter
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasManager
import pe.edu.ulima.pm.asesoriasulima.model.LoginManager
import pe.edu.ulima.pm.asesoriasulima.radioButtonGroup
import pe.edu.ulima.pm.asesoriasulima.rol

class RegistrarAsesoriaFragment(var cod: String) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registrar_asesoria_profesor, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var arreglo: ArrayList<String> = arrayListOf(
            "8 - 9 am",
            "9 - 10 am",
            "10 - 11 am",
            "11 - 12 am",
            "12 - 1 pm",
            "2 - 3 pm",
            "3 - 4 pm",
            "4 - 5 pm",
            "5 - 6 pm",
            "7 - 8 pm",
            "8 - 9 pm",
            "9 - 10 pm"
        )

        var arreglo_dias: ArrayList<String> = arrayListOf(
            "Lunes",
            "Martes",
            "Miercoles",
            "Jueves",
            "Viernes"
        )

        println("ENTRA:" + cod)

        var autocomplete: AutoCompleteTextView
        var adapterItems: ArrayAdapter<String>

        var autocompleteDia: AutoCompleteTextView
        var adapterItemsDia: ArrayAdapter<String>

        var horario: String? = null
        var dia:String?=null

        //config select horario
        autocomplete = view.findViewById(R.id.auto_complete_txt)
        adapterItems = ArrayAdapter<String>(requireContext(), R.layout.list_item, arreglo)
        autocomplete.setAdapter(adapterItems)
        autocomplete.setOnItemClickListener { adapterView, view, i, l ->
            var item: String = adapterView.getItemAtPosition(i).toString()
            horario = item
        }
        //config select dia
        autocompleteDia = view.findViewById(R.id.auto_complete_txt_Dia)
        adapterItemsDia = ArrayAdapter<String>(requireContext(), R.layout.list_item_dias, arreglo_dias)
        autocompleteDia.setAdapter(adapterItemsDia)
        autocompleteDia.setOnItemClickListener { adapterView, view, i, l ->
            var item2: String = adapterView.getItemAtPosition(i).toString()
            dia = item2
        }

        var nombreCurso = view.findViewById<EditText>(R.id.etcursoAregistrar)
        var seccion_Curso = view.findViewById<EditText>(R.id.etseccionaRegistrar)
        var url= view.findViewById<EditText>(R.id.eturl)
        val btnRegistrar = view.findViewById<Button>(R.id.btnRegistrarAsesoria)

        btnRegistrar.setOnClickListener {
            if (nombreCurso.text.toString() != "" && seccion_Curso.text.toString() != "" && horario != null && horario != ""&& dia != null && dia != "" && url.text.toString()!="") {
                registrarAsesoriaToFirebase(
                    nombreCurso.text.toString(),
                    seccion_Curso.text.toString(),
                    horario.toString(),
                    cod,
                    dia.toString(),
                    url.text.toString()
                )
                nombreCurso.setText("")
                seccion_Curso.setText("")
                autocomplete.setText("")
                autocomplete.clearFocus()
                horario = ""

                autocompleteDia.setText("")
                autocompleteDia.clearFocus()
                dia=""
                url.setText("")
            } else {
                Toast.makeText(requireContext(), "Campos Obligatorios", Toast.LENGTH_SHORT).show()

            }

        }


    }

    private fun registrarAsesoriaToFirebase(
        curso: String,
        seccion: String,
        horario: String,
        codigo_profe: String,
        dia:String,
        url:String
    ) {
        AsesoriasManager.instance.registrarAsesoriaEnFirebase(
            curso,
            seccion,
            horario,
            codigo_profe,
            dia,
            url,
            {
                Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()

            },
            {
                Toast.makeText(requireContext(), "Error al registrar", Toast.LENGTH_SHORT).show()

            })
    }
}
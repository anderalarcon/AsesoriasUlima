package pe.edu.ulima.pm.asesoriasulima.fragments.profesor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.adapter.AsesoriasAlumnosAdapter
import pe.edu.ulima.pm.asesoriasulima.adapter.adapterProfesor.MisAsesoriasProfesorAdapter
import pe.edu.ulima.pm.asesoriasulima.fragments.AsesoriasAlumnoFragment
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasAlumno
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasManager

class MisAsesoriasFragment(var cod: String):Fragment() {
    interface interfaceAsesoriasProfesor{
        fun ChangeToRegistrarNuevaAsesoria()
        fun onSelectCardAsesoriaProfesor(cardAsesoria:Asesorias)
    }



    private var listener: interfaceAsesoriasProfesor?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceAsesoriasProfesor
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mis_asesorias_profesor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val btnRegistrar=view.findViewById<Button>(R.id.RegistrarAsesoriaProfesor)
        btnRegistrar.setOnClickListener{
            listener!!.ChangeToRegistrarNuevaAsesoria()
        }

        val rviAsesorias = view.findViewById<RecyclerView>(R.id.ReciclerMisAsesoriasProfesor)
        AsesoriasManager.instance.getAsesoriasFirebase(cod,{res:List<Asesorias>->
            rviAsesorias.adapter=MisAsesoriasProfesorAdapter(res,this){
                asesoria:Asesorias->
                listener?.onSelectCardAsesoriaProfesor(asesoria)

            }
        },{error->
            Log.e("MisAsesoriasFragment",error)
        })



    }
}
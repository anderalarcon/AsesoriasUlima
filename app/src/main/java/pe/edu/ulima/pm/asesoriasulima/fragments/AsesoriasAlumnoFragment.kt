package pe.edu.ulima.pm.asesoriasulima.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.appcompat.widget.SearchView
import android.widget.SearchView;
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.adapter.AsesoriasAlumnosAdapter
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasAlumno
import java.util.*

class AsesoriasAlumnoFragment: Fragment() {



    interface interfaceAsesoriasALumnos{
        fun ChangeVerDetalle(Asesorias : Asesorias)
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


   /*     val listaAsesorias = arrayListOf<Asesorias>()
        var listasAsesoriasFiltrado = arrayListOf<Asesorias>()
        listaAsesorias.add(Asesorias(1,"Programación móvil","801","Hernan Quintana","lunes 9 - 10"))
        listaAsesorias.add(Asesorias(2,"Ingenieria de SW II","801","Nina Hanco","miercoles 9 - 10"))
        listasAsesoriasFiltrado.addAll(listaAsesorias)*/
        /*listaAsesorias.add(AsesoriasAlumno(2,"Ingenieria de SW II","801","Nina Hanco","miercoles 9 - 10"))
        listaAsesorias.add(AsesoriasAlumno(2,"Ingenieria de SW II","801","Nina Hanco","miercoles 9 - 10"))
        listaAsesorias.add(AsesoriasAlumno(2,"Ingenieria de SW II","801","Nina Hanco","miercoles 9 - 10"))*/

        val sviCursoAlumnoBuscar = view.findViewById<SearchView>(R.id.sviCursoAlumnoBuscar)



/*        val rviAsesorias = view.findViewById<RecyclerView>(R.id.rviAsesoriasAlumnos)
        rviAsesorias.adapter = AsesoriasAlumnosAdapter(
            listaAsesorias,
            this@AsesoriasAlumnoFragment
        ){Asesorias : Asesorias ->
            listener?.ChangeVerDetalle(Asesorias)
        }

        sviCursoAlumnoBuscar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               if(newText!!.isNotEmpty()){
                   listaAsesorias.clear()
                   var search = newText.toLowerCase(Locale.getDefault())

                   for(asesoria in listasAsesoriasFiltrado){
                       if(asesoria.nombreCurso?.toLowerCase(Locale.getDefault())!!.contains(search)){
                           listaAsesorias.add(asesoria)
                       }
                       rviAsesorias.adapter!!.notifyDataSetChanged()

                   }
               }else{
                   listaAsesorias.clear()
                   listaAsesorias.addAll(listasAsesoriasFiltrado)
                   rviAsesorias.adapter!!.notifyDataSetChanged()
               }

                return true
            }
        }*/
       /* )*/






    }
}
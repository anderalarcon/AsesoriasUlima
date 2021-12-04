package pe.edu.ulima.pm.asesoriasulima.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.textservice.TextInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasAlumno

class AsesoriasAlumnosAdapter(
    private val ListAsesoriasAlumno: List<Asesorias>,
    //private val ListAsesoriasAlumnoFILTRADA: List<Asesorias>,
    private val fragment: Fragment,
    private val listener: (Asesorias) -> Unit
) : RecyclerView.Adapter<AsesoriasAlumnosAdapter.ViewHolder>(){
    class ViewHolder(view: View, val listener: (Asesorias) -> Unit, val ListAsesorias: List<Asesorias>):
        RecyclerView.ViewHolder(view), View.OnClickListener {

        val tviCursoAsesoriaAlumno : TextView
        val tviSeccionAsesoriaAlumno : TextView
        val tviProfesorAsesoriaAlumno : TextView
        val tviListaAsesoriaAlumno : TextView

        init {

            tviCursoAsesoriaAlumno = view.findViewById(R.id.tviCursoAsesoriaAlumno)
            tviSeccionAsesoriaAlumno = view.findViewById(R.id.tviSeccionAsesoriaAlumno)
            tviProfesorAsesoriaAlumno = view.findViewById(R.id.tviProfesorAsesoriaAlumno)
            tviListaAsesoriaAlumno = view.findViewById(R.id.tviListaAsesoriaAlumno)
            view.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            listener(ListAsesorias[adapterPosition])
        }


    }

   /* public fun filtrado(txtBuscar : String){
        val longitudString = txtBuscar.length
        if(longitudString==0){
            ListAsesoriasAlumno.

        }
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso_alumno, parent, false)

        val viewHolder = ViewHolder(view, listener, ListAsesoriasAlumno)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviCursoAsesoriaAlumno.text = ListAsesoriasAlumno[position].nombreCurso
        holder.tviSeccionAsesoriaAlumno.text = "Sección: ${ListAsesoriasAlumno[position].seccion}"
        holder.tviProfesorAsesoriaAlumno.text = "Profesor: ${ListAsesoriasAlumno[position].codigo_profe}"
        holder.tviListaAsesoriaAlumno.text = ListAsesoriasAlumno[position].horario + ", " + ListAsesoriasAlumno[position].dia

    }

    override fun getItemCount(): Int {
        return ListAsesoriasAlumno.size
    }

}
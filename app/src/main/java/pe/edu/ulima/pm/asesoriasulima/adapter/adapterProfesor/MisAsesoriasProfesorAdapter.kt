package pe.edu.ulima.pm.asesoriasulima.adapter.adapterProfesor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.adapter.AsesoriasAlumnosAdapter

import pe.edu.ulima.pm.asesoriasulima.model.Asesorias


class MisAsesoriasProfesorAdapter(
    private val ListAsesoriasProfesor: List<Asesorias>,
    private val fragment: Fragment,
    private val listener: (Asesorias) -> Unit
) : RecyclerView.Adapter<MisAsesoriasProfesorAdapter.ViewHolder>() {
    class ViewHolder(
        view: View,
        val listener: (Asesorias) -> Unit,
        val ListAsesorias: List<Asesorias>
    ) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        val tviCursoAsesoria: TextView
        val tviSeccionAsesoria: TextView

        val tviListaAsesoria: TextView

        init {

            tviCursoAsesoria = view.findViewById(R.id.curso_asesoria)
            tviSeccionAsesoria = view.findViewById(R.id.seccion_asesoria)
            tviListaAsesoria = view.findViewById(R.id.dias_asesorias)
            view.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            listener(ListAsesorias[adapterPosition])
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_asesoria_profesor, parent, false)

        val viewHolder = MisAsesoriasProfesorAdapter.ViewHolder(view, listener, ListAsesoriasProfesor)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviCursoAsesoria.text = ListAsesoriasProfesor[position].nombreCurso
        holder.tviSeccionAsesoria.text = "Sección: ${ListAsesoriasProfesor[position].seccion}"
        holder.tviListaAsesoria.text = ListAsesoriasProfesor[position].asesorias
    }

    override fun getItemCount(): Int {
        return ListAsesoriasProfesor.size
    }


}
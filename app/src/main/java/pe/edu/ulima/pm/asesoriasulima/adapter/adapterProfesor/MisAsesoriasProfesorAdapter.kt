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
        val tvihorario: TextView
        val tviDia: TextView
        init {

            tviCursoAsesoria = view.findViewById(R.id.curso_asesoria)
            tviDia=view.findViewById(R.id.dia_asesoria)
            tviSeccionAsesoria = view.findViewById(R.id.seccion_asesoria)
            tvihorario = view.findViewById(R.id.horario_asesoria)
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
        holder.tviDia.text = "Días: ${ListAsesoriasProfesor[position].dia}"
        holder.tvihorario.text = "Horarios: ${ListAsesoriasProfesor[position].horario}"

    }

    override fun getItemCount(): Int {
        return ListAsesoriasProfesor.size
    }


}
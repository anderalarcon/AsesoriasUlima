package pe.edu.ulima.pm.asesoriasulima.adapter.adapterProfesor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.model.Asesorias
import pe.edu.ulima.pm.asesoriasulima.model.Participantes
import pe.edu.ulima.pm.asesoriasulima.model.Registro

class AsistentesAdapter(
    private val listAsistentes: List<HashMap<String,String>>,
    private val fragment: Fragment,
    private val listener: (Participantes) -> Unit
) : RecyclerView.Adapter<AsistentesAdapter.ViewHolder>() {
    class ViewHolder(
        view: View,
        val listener: (Participantes) -> Unit,
        val ListParticipantes: List<HashMap<String,String>>
    ) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        val tviCodigoAsistente: TextView
        val tvimotivoAsistente: TextView

        init {

            tviCodigoAsistente = view.findViewById(R.id.tviCodigo_Asistente_Profesor)
            tvimotivoAsistente = view.findViewById(R.id.tviMotivo_Asistente_Profesor)

            view.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
         /*   listener(ListParticipantes[adapterPosition])*/
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_asistente_profesor, parent, false)

        val viewHolder = AsistentesAdapter.ViewHolder(view, listener, listAsistentes)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tviCodigoAsistente.text = "Codigo: "+listAsistentes[position]["codigo"]
        holder.tvimotivoAsistente.text = "Motivo: "+listAsistentes[position]["motivo"]

    }

    override fun getItemCount(): Int {
        return listAsistentes.size
    }


}
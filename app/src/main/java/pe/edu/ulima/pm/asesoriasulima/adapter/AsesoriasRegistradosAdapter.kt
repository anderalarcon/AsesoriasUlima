package pe.edu.ulima.pm.asesoriasulima.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.model.RegistroFirebase
import pe.edu.ulima.pm.asesoriasulima.model.asistente

class AsesoriasRegistradosAdapter(
    private val ListRegistrados : List<asistente>,
    private val fragment: Fragment,
    private val listener: (asistente) -> Unit

) : RecyclerView.Adapter<AsesoriasRegistradosAdapter.ViewHolder>(){
    class ViewHolder(view: View, val listener: (asistente) -> Unit, val listRegistros: List<asistente>):
        RecyclerView.ViewHolder(view), View.OnClickListener {

            val tviOrdenAlumnoDETALLELISTA: TextView
            val tviNombreAlumnoDETALLELISTA: TextView
            val tviMotivoAlumnoDETALLELISTA:TextView

            init{
                tviOrdenAlumnoDETALLELISTA = view.findViewById(R.id.tviOrdenAlumnoDETALLELISTA)
                tviNombreAlumnoDETALLELISTA = view.findViewById(R.id.tviNombreAlumnoDETALLELISTA)
                tviMotivoAlumnoDETALLELISTA = view.findViewById(R.id.tviMotivoAlumnoDETALLELISTA)
                view.setOnClickListener(this)
            }
        override fun onClick(v: View?) {
            listener(listRegistros[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_alumnos_registrados, parent, false)

            val viewHolder = ViewHolder(view, listener, ListRegistrados)
            return viewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviOrdenAlumnoDETALLELISTA.text = position.toString()
        holder.tviNombreAlumnoDETALLELISTA.text = ListRegistrados[position].codigo
        holder.tviMotivoAlumnoDETALLELISTA.text = ListRegistrados[position].motivo

    }

    override fun getItemCount(): Int {
        return ListRegistrados.size
    }


}
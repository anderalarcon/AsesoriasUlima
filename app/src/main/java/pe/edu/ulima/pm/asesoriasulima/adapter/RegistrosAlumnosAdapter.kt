package pe.edu.ulima.pm.asesoriasulima.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.asesoriasulima.R
import pe.edu.ulima.pm.asesoriasulima.model.AsesoriasAlumno
import pe.edu.ulima.pm.asesoriasulima.model.RegistrosAlumnos

class RegistrosAlumnosAdapter(
    private val ListRegistrosAlumno: List<RegistrosAlumnos>,
    private val fragment: Fragment,
    private val listener: (RegistrosAlumnos) -> Unit
): RecyclerView.Adapter<RegistrosAlumnosAdapter.ViewHolder>(){
    class ViewHolder(view: View, val listener: (RegistrosAlumnos) -> Unit, val ListRegistros: List<RegistrosAlumnos>):
        RecyclerView.ViewHolder(view), View.OnClickListener {

        val tviCursoRegistroAlumno : TextView
        val tviMotivoAsesoriaREGISTRO : TextView
        val tviSeccionRegistroAlumno : TextView
        val tviProfesorRegistroAlumno : TextView
        val tviFechaAsesoriaAlumno : TextView
        val tviOrdenAlumno: TextView

        init {
            tviCursoRegistroAlumno = view.findViewById(R.id.tviCursoRegistroAlumno)
            tviMotivoAsesoriaREGISTRO = view.findViewById(R.id.tviMotivoAsesoriaREGISTRO)
            tviSeccionRegistroAlumno = view.findViewById(R.id.tviSeccionRegistroAlumno)
            tviProfesorRegistroAlumno = view.findViewById(R.id.tviProfesorRegistroAlumno)
            tviFechaAsesoriaAlumno = view.findViewById(R.id.tviFechaAsesoriaAlumno)
            tviOrdenAlumno = view.findViewById(R.id.tviOrdenAlumno)
            view.setOnClickListener(this)
            view.findViewById<Button>(R.id.butEnlaceAsesoriaAlumnoREGISTRO).setOnClickListener {
                val strEnlace = ListRegistros[adapterPosition].enlace
                val clipboard = itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
                val clip = ClipData.newPlainText("Copiar el en portatapeles", strEnlace)
                clipboard!!.setPrimaryClip(clip)
                val myToast: Toast = Toast.makeText(itemView.context, "Copiado en el portapapeles", Toast.LENGTH_LONG)
                myToast.show()

            }
        }

        override fun onClick(p0: View?) {
            listener(ListRegistros[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro_alumnos, parent, false)

        val viewHolder = ViewHolder(view, listener, ListRegistrosAlumno)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviCursoRegistroAlumno.text = ListRegistrosAlumno[position].nombreCurso
        holder.tviMotivoAsesoriaREGISTRO.text = "Motivo: ${ListRegistrosAlumno[position].motivoAsesoria}"
        holder.tviSeccionRegistroAlumno.text = "Sección: ${ListRegistrosAlumno[position].seccion}"
        holder.tviProfesorRegistroAlumno.text = "Profesor: ${ListRegistrosAlumno[position].profesor}"
        holder.tviFechaAsesoriaAlumno.text = "Fecha: ${ListRegistrosAlumno[position].fechaAsesoria}"
        holder.tviOrdenAlumno.text = "Orden: ${ListRegistrosAlumno[position].orden}"
    }

    override fun getItemCount(): Int {
        return ListRegistrosAlumno.size
    }

}

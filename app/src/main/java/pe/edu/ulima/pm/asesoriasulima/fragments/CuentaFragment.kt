package pe.edu.ulima.pm.asesoriasulima.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.asesoriasulima.R

class CuentaFragment:Fragment() {

    interface interfaceCuentaAlumnos{
        fun UpdateCuentaAlumno(NewNombre: String , NewPassword: String)
    }

    private var listener: interfaceCuentaAlumnos? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceCuentaAlumnos
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonActualizar = view.findViewById<Button>(R.id.butActualizarCuenta)
        buttonActualizar.setOnClickListener {
            listener?.UpdateCuentaAlumno(
                view.findViewById<EditText>(R.id.eteActualizarNombre).text.toString(),
                view.findViewById<EditText>(R.id.eteActualizarContra).text.toString()

            )
        }



    }
}
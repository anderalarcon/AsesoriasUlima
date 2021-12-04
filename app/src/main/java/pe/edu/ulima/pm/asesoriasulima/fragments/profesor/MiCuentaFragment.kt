package pe.edu.ulima.pm.asesoriasulima.fragments.profesor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.asesoriasulima.R

class MiCuentaFragment:Fragment() {

    interface interfaceCuentaProfe{
        fun UpdateCuentaProfe(NewNombre: String , NewPassword: String)
    }

    private var listener: interfaceCuentaProfe? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? interfaceCuentaProfe
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mi_cuenta_profesor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonActualizarProfe = view.findViewById<Button>(R.id.butActualizarCuentaProfesor)
        buttonActualizarProfe.setOnClickListener {
            listener?.UpdateCuentaProfe(
                view.findViewById<EditText>(R.id.eteActualizarNombreProfe).text.toString(),
                view.findViewById<EditText>(R.id.eteActualizarContraProfe).text.toString()
            )
            view.findViewById<EditText>(R.id.eteActualizarNombreProfe).setText("")
            view.findViewById<EditText>(R.id.eteActualizarContraProfe).setText("")
        }
    }
}
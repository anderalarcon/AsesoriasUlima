package pe.edu.ulima.pm.asesoriasulima.fragments.profesor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.asesoriasulima.R

class MiCuentaFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mi_cuenta_profesor, container, false)
    }
}
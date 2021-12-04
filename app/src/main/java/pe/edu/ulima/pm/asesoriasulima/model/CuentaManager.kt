package pe.edu.ulima.pm.asesoriasulima.model

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CuentaManager {


    companion object {
        var instance : CuentaManager = CuentaManager()
            private set
    }

    private val dbFirebase = Firebase.firestore

     fun updateUsuarioAlumno(id: Long, NewNombre: String, NewContra: String){
        if(NewContra.equals("") && !NewNombre.equals("")){
            println("Actualiza NOMBRE, no Actualiza CONTRA")
            dbFirebase.collection("users").document(id.toString())
                .update("nombre",NewNombre)
                .addOnSuccessListener { Log.d("CuentaManager", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("CuentaManager", "Error updating document", e) }
        } else if(!NewContra.equals("") && NewNombre.equals("")){
            println("NO Actualiza NOMBRE, Actualiza CONTRA")
            dbFirebase.collection("users").document(id.toString())
                .update("password",NewContra)
                .addOnSuccessListener { Log.d("CuentaManager", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("CuentaManager", "Error updating document", e) }
        }else if(NewContra.equals("") && NewNombre.equals("")){
            println("COMPLETE DATOS")
        }
        else{
            println("ACTUALIZA LOS 2")
            dbFirebase.collection("users").document(id.toString())
                .update("nombre",NewNombre,"password",NewContra)
                .addOnSuccessListener { Log.d("CuentaManager", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("CuentaManager", "Error updating document", e) }
        }



    }

    fun updateUsuarioProfe(id: Long, NewNombre: String, NewContra: String){
        if(NewContra.equals("") && !NewNombre.equals("")){
            dbFirebase.collection("users").document(id.toString())
                .update("nombre",NewNombre)
                .addOnSuccessListener { Log.d("CuentaManager", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("CuentaManager", "Error updating document", e) }
        } else if(!NewContra.equals("") && NewNombre.equals("")){
            dbFirebase.collection("users").document(id.toString())
                .update("password",NewContra)
                .addOnSuccessListener { Log.d("CuentaManager", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("CuentaManager", "Error updating document", e) }
        }else if(NewContra.equals("") && NewNombre.equals("")){
            println("COMPLETE DATOS")
        }
        else{
            dbFirebase.collection("users").document(id.toString())
                .update("nombre",NewNombre,"password",NewContra)
                .addOnSuccessListener { Log.d("CuentaManager", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("CuentaManager", "Error updating document", e) }
        }



    }

}
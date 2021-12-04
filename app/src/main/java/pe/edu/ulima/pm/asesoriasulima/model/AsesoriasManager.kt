package pe.edu.ulima.pm.asesoriasulima.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AsesoriasManager {
    companion object {
        var instance: AsesoriasManager = AsesoriasManager()
            private set
    }

    private val dbFirebase = Firebase.firestore

    fun registrarAsesoriaEnFirebase(
        curso: String,
        seccion: String,
        horario:String,
        codigo_profe:String,
        dia:String,
        url:String,
        callbackOk: (Long) -> Unit,
        callbackError: (String) -> Unit
    ) {

        val data = hashMapOf<String, Any>(
            "curso" to curso,
            "seccion" to seccion,
            "horario" to horario,
            "codigo_profe" to codigo_profe,
            "dia" to dia,
            "url" to url

            )

        val asesoriaId = System.currentTimeMillis().toString()
        dbFirebase.collection("asesorias").document(asesoriaId).set(data)
            .addOnSuccessListener {
                callbackOk(asesoriaId.toLong())
            }.addOnFailureListener {
                callbackError(it.message!!)
            }
    }

}
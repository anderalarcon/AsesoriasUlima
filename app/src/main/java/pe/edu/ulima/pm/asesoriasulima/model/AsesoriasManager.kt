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

    fun getAsesoriasFirebase(
        codigo_profe: String,
        callbackOK: (List<Asesorias>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("asesorias").whereEqualTo("codigo_profe",codigo_profe).get().addOnSuccessListener { res ->
            val asesorias = arrayListOf<Asesorias>()
            for (document in res) {
                val asesoria = Asesorias(
                    document.id.toLong(),
                    document.data["curso"] as String?,
                    document.data["dia"] as String?,
                    document.data["horario"] as String?,
                    document.data["seccion"] as String?,
                    document.data["codigo_profe"] as String?,
                    document.data["url"] as String?,

                )
                asesorias.add(asesoria)

            }
            callbackOK(asesorias)
        }.addOnFailureListener {
            callbackError(it.message!!)
        }
    }

    fun getProfeNombew() : String{
        var auxProfe : String = ""
        dbFirebase.collection("users").whereEqualTo("codigo", "7020171234").get().addOnSuccessListener { res ->
            for(document in res){
                println("PROFE XD: " + document.data["nombre"] as String)
                auxProfe = document.data["nombre"] as String
            }
        }
        return auxProfe
    }

    fun getAsesoriasFirebaseAlumno(
        callbackOk: (List<Asesorias>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        /*val capitalCities = dbFirebase.collection("users").whereEqualTo("codigo", "7020171234")
        println("PROFE: " + capitalCities.firestore.)*/

        println("PROFE: "  + getProfeNombew())

        dbFirebase.collection("asesorias").get().addOnSuccessListener { res ->
            val Asesorias = arrayListOf<Asesorias>()

            for (document in res) {
                val asesoria = Asesorias(
                    document.id.toLong(),
                    document.data["curso"] as String?,
                    document.data["dia"] as String?,
                    document.data["horario"] as String?,
                    document.data["seccion"] as String?,
                    document.data["codigo_profe"] as String?,
                    document.data["url"] as String?
                )
                Asesorias.add(asesoria)
            }
            callbackOk(Asesorias)
            println(Asesorias)
        }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }

}
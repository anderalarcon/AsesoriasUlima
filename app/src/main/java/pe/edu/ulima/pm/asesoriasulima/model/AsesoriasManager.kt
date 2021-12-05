package pe.edu.ulima.pm.asesoriasulima.model


import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FieldValue

import com.google.firebase.firestore.FieldPath

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AsesoriasManager {
    companion object {
        var instance: AsesoriasManager = AsesoriasManager()
            private set
    }

    val UsuariosGlobal = arrayListOf<UsuarioFirebase>()
    val registrosGlobal = arrayListOf<RegistroFirebase>()
    val registrosGlobalTODOS = arrayListOf<RegistroFirebase>()

    private val dbFirebase = Firebase.firestore

    fun registrarAsesoriaEnFirebase(
        curso: String,
        seccion: String,
        horario: String,
        codigo_profe: String,
        dia: String,
        url: String,
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
        dbFirebase.collection("asesorias").whereEqualTo("codigo_profe", codigo_profe).get()
            .addOnSuccessListener { res ->
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

    fun getProfeNombre(
        callbackOk: (List<UsuarioFirebase>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("users").get().addOnSuccessListener { res ->
            val Usuarios = arrayListOf<UsuarioFirebase>()
            for (document in res) {
                val usuario = UsuarioFirebase(
                    document.id.toLong(),
                    document.data["codigo"] as String,
                    document.data["nombre"] as String,
                    document.data["password"] as String,
                    document.data["rol"] as String
                )
                Usuarios.add(usuario)
            }
            callbackOk(Usuarios)
        }.addOnFailureListener {
            callbackError(it.message!!)
        }

    }

    fun buscarNombreProfe(Usuarios: List<UsuarioFirebase>, codigo_profe: String): String {
        var usuario = ""
        for (user in Usuarios) {
            if (user.codigo.toString() == codigo_profe) {
                usuario = user.nombre
            }
        }
        return usuario

    }

    fun getProfeNombew(): String {
        var auxProfe: String = ""
        dbFirebase.collection("users").whereEqualTo("codigo", "7020171234").get()
            .addOnSuccessListener { res ->
                for (document in res) {
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

        //println("PROFE: "  + getProfeNombew())


        getProfeNombre({ res: List<UsuarioFirebase> ->
            UsuariosGlobal.addAll(res)
        }, { error ->
            println(error)

        }
        )

        println("PROFE: " + getProfeNombew())
        dbFirebase.collection("asesorias").get().addOnSuccessListener { res ->
            val Asesorias = arrayListOf<Asesorias>()

            for (document in res) {
                val asesoria = Asesorias(
                    document.id.toLong(),
                    document.data["curso"] as String?,
                    document.data["dia"] as String?,
                    document.data["horario"] as String?,
                    document.data["seccion"] as String?,
                    buscarNombreProfe(
                        UsuariosGlobal,
                        document.data["codigo_profe"].toString()
                    ) as String?,
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


    fun GetRegistrosFirebase(
        callbackOk: (List<RegistroFirebase>) -> Unit,
        callbackError: (String) -> Unit,
        idAsesoria: String
    ) {
        dbFirebase.collection("registro").whereEqualTo("asesoriaid", idAsesoria).limit(1).get()
            .addOnSuccessListener { res ->
                val Registros = arrayListOf<RegistroFirebase>()
                for (document in res) {
                    val Registro = RegistroFirebase(
                        document.id,
                        document.data["asesoriaid"] as String,
                        document.data["asistente"] as List<asistente>
                    )
                    Registros.add(Registro)
                }
                callbackOk(Registros)
                // registrosGlobal.addAll(Registros)
            }.addOnFailureListener {
            callbackError(it.message!!)
        }
    }

    fun GetListaAsistentesFirebase(
        callbackOk: (List<asistente>) -> Unit,
        callbackError: (String) -> Unit,
        idAsesoria: String
    ) {
        dbFirebase.collection("registro").whereEqualTo("asesoriaid", idAsesoria).limit(1).get()
            .addOnSuccessListener { res ->
                val Registros = arrayListOf<RegistroFirebase>()
                val asistentes = arrayListOf<asistente>()
                for (document in res) {
                    var count = 0
                    val registro = RegistroFirebase(
                        document.id,
                        document.data["asesoriaid"] as String,
                        document.data["asistente"] as List<asistente>
                    )
                    Registros.add(registro)
                }
                println("REGISTRO: " + Registros)
                println("Cantidad de asistentes: " + Registros[0].asistente.size)
                println("ASISTENTES: " + Registros[0].asistente.toString())
                var xd = Registros[0].asistente.toString()
                xd.replace("{","")
                println("xD:" + xd)
               /* for (i in Registros[0].asistente.toList()) {
                    val participante = asistente(
                        i.codigo,
                        i.motivo
                    )

                    asistentes.add(participante)
                }*/




                val participante = asistente("20174017","xd")
                asistentes.add(participante)
                callbackOk(asistentes)
                // registrosGlobal.addAll(Registros)
            }.addOnFailureListener {
            callbackError(it.message!!)
        }
    }

    fun RegistrarAsesoriaYAlumno(
        idAsesoria: String,
        asistente: asistente,
        callbackOK: (Long) -> Unit,
        callbackError: (String) -> Unit
    ) {
        /*val auxRegistro = arrayListOf<asistente>()
        auxRegistro.add(codigoAlumno)*/
        val userId = System.currentTimeMillis()
        val data = hashMapOf(
            "asesoriaid" to idAsesoria,
            "asistente" to arrayListOf(asistente)
        )
        dbFirebase.collection("registro").document(userId.toString())
            .set(data)
            .addOnSuccessListener {
                callbackOK(userId)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }


    fun RegistrarAlumnoEnAsesoria(
        idAsesoria: String,
        asistente: asistente
    )/*codigoAlumno: String, motivo: String )*/ {
        //Buscar en coleccion registro en base del id del codigo, si el count = 0,
        //hacer un nuevo registro
        //actualizar el campo array (agregar al participante) de la asesoria existente
        registrosGlobal.clear()
        GetRegistrosFirebase({ res: List<RegistroFirebase> ->
            registrosGlobal.addAll(res)
            println("REGISTRO: " + registrosGlobal)
            if (registrosGlobal.size == 1) {
                println("ACTUALIZAAAAAAAAAR")
                dbFirebase.collection("registro").document(registrosGlobal[0].id)
                    .update("asistente", FieldValue.arrayUnion(asistente))
                    .addOnSuccessListener {
                        Log.d(
                            "AsesoriasManager",
                            "DocumentSnapshot successfully updated!"
                        )
                    }
                    .addOnFailureListener { e ->
                        Log.w(
                            "AsesoriasManager",
                            "Error Updating document",
                            e
                        )
                    }
            } else {
                println("CREAAAAAAAR")
                RegistrarAsesoriaYAlumno(idAsesoria, asistente,
                    { println(it) },
                    { Log.e("RegistrarAsesoriaFragmt", it) })
            }
        }, { error -> println(error) },
            idAsesoria.toString()
        )
    }

    //PARA LOS REGISTROS DEL ALUMNO

    fun GetRegistrosFirebaseTODOS(
        callbackOk: (List<RegistroFirebase>) -> Unit,
        callbackError: (String) -> Unit,
        idAsesoria: String
    ) {
        dbFirebase.collection("registro").whereEqualTo("asesoriaid", idAsesoria).limit(1).get()
            .addOnSuccessListener { res ->
                val Registros = arrayListOf<RegistroFirebase>()
                for (document in res) {
                    val Registro = RegistroFirebase(
                        document.id,
                        document.data["asesoriaid"] as String,
                        document.data["asistente"] as List<asistente>
                    )
                    Registros.add(Registro)
                }
                callbackOk(Registros)
                // registrosGlobal.addAll(Registros)
            }.addOnFailureListener {
                callbackError(it.message!!)
            }
    }

   /* fun BuscarAlumnoEnRegistro(

    )*/

    fun GetRegistrosAlumnoFirebase(
        callbackOk: (List<RegistrosAlumnos>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        getProfeNombre({ res: List<UsuarioFirebase> ->
            UsuariosGlobal.addAll(res)
        }, { error ->
            println(error)

        }
        )

        dbFirebase.collection("asesorias").get().addOnSuccessListener { res ->
            val Asesorias = arrayListOf<RegistrosAlumnos>()

            for (document in res) {
                var fecha = "${document.data["dia"]} , ${document.data["horario"]}"
                val asesoria = RegistrosAlumnos(
                    document.id.toLong(),
                    document.data["curso"] as String?,
                    "xd",//FUNCION PA BUSCAR EL MOTIVO
                    document.data["seccion"] as String?,
                    buscarNombreProfe(UsuariosGlobal, document.data["codigo_profe"].toString()) as String?,
                    fecha as String?,
                    "1",
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

    fun getRegistroProfe(
        id_Asesoria:String,
        callbackOk: (Registro) -> Unit,
        callbackError: (String) -> Unit
    ) {

        dbFirebase.collection("registro").whereEqualTo("asesoriaid",id_Asesoria).get().addOnSuccessListener { res ->
            var Registro:Registro?=null
            for (doc in res) {
                val registro = Registro(
                    doc.id.toLong(),
                    doc.data["asistente"] as List<HashMap<String,String>>
                )
                Registro=registro

            }
            callbackOk(Registro!!)

        }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }
}
package pe.edu.ulima.pm.asesoriasulima.model

data class RegistroAsistencia(
    var id :Long,
    var asesoriaid: String,
    var asistente:List<HashMap<String,String>>

)

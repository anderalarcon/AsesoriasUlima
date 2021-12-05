package pe.edu.ulima.pm.asesoriasulima.model

data class Registro(
    var asesoria_id :Long,
    var asistente:List<HashMap<String,String>>
)
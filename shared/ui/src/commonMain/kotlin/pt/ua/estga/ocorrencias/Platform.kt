package pt.ua.estga.ocorrencias

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
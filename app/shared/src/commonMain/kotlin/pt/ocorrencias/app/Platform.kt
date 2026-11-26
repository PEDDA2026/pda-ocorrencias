package pt.ocorrencias.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
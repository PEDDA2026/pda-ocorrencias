package ocorrencias.grupo2.pda.estga

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
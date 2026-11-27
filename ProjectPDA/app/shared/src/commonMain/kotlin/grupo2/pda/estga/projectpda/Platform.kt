package grupo2.pda.estga.projectpda

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
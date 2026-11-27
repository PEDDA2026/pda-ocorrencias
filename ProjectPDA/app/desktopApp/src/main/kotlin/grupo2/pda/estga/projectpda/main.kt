package grupo2.pda.estga.projectpda

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "projectpda",
    ) {
        App()
    }
}
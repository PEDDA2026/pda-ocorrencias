package pt.ua.estga.ocorrencias

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Ocorrências",
    ) {
        App()
    }
}
package pl.rigo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import pl.rigo.di.serverModule
import pl.rigo.di.shareModule

fun koinInit() {
    startKoin {
        modules(
            serverModule,
            shareModule,
        )
    }
}

fun main() {
    koinInit()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            alwaysOnTop = true,
            title = "LANShare",
        ) {
            App()
        }
    }
}

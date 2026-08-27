package pl.rigo.utils
import java.awt.Desktop
import java.io.File

actual fun getUserDocumentsPath(): String {
    val userHome = System.getProperty("user.home")
    val os = System.getProperty("os.name").lowercase()

    val docsDir =
        when {
            os.contains("win") -> File(userHome, "Documents")
            os.contains("mac") -> File(userHome, "Documents")
            else -> File(userHome, "Documents") // most of linux distributions
        }

    return if (docsDir.exists()) docsDir.absolutePath else userHome
}

actual fun openFolderInExplorer(path: String) {
    val file = File(path)
    if (!file.exists()) return

    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
        Desktop.getDesktop().open(file)
    } else {
        // Fallback for systems where Desktop API is not supported
        val os = System.getProperty("os.name").lowercase()
        when {
            os.contains("win") -> ProcessBuilder("explorer.exe", file.absolutePath).start()
            os.contains("mac") -> ProcessBuilder("open", file.absolutePath).start()
            os.contains("nix") || os.contains("nux") -> ProcessBuilder("xdg-open", file.absolutePath).start()
        }
    }
}

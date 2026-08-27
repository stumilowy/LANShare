package pl.rigo.features.dashboard.domain

import javax.swing.JFileChooser
import javax.swing.UIManager

actual fun openDirectoryPicker(): String? {
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    } catch (ignored: Exception) {
    }

    val chooser = JFileChooser()
    chooser.setDialogTitle("Select Directory")
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
    chooser.setAcceptAllFileFilterUsed(false)

    val result = chooser.showOpenDialog(null)
    if (result == JFileChooser.APPROVE_OPTION) {
        val selectedDirectory = chooser.selectedFile
        return selectedDirectory?.absolutePath
    }
    return null
}

package pl.rigo.features.dashboard.domain

import androidx.compose.runtime.Composable
import java.nio.file.Path

expect class FilePicker {
    fun openDirectoryPicker(): Path?
}

@Composable
expect fun rememberFilePicker(): FilePicker {}
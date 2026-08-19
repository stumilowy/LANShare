package pl.rigo.features.dashboard.domain

import androidx.compose.runtime.Composable

expect class FilePicker {
    fun openDirectoryPicker(): String?
}

@Composable
expect fun rememberFilePicker(): FilePicker
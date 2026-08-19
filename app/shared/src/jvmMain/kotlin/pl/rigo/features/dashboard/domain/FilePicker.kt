package pl.rigo.features.dashboard.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.nio.file.Path

actual class FilePicker {
    actual fun openDirectoryPicker(): String? {
        return null;
    }
}


@Composable
actual fun rememberFilePicker(): FilePicker { return remember { FilePicker() } }
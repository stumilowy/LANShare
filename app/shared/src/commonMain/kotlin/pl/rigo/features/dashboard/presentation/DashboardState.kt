package pl.rigo.features.dashboard.presentation

import pl.rigo.features.dashboard.domain.SharedFile
import pl.rigo.utils.getUserDocumentsPath

data class DashboardState(
    val isServerRunning: Boolean = false,
    val isServerLoading: Boolean = false,
    val lastSharedFiles: List<SharedFile> = emptyList(),
    val destinyPath: String = getUserDocumentsPath(),
)

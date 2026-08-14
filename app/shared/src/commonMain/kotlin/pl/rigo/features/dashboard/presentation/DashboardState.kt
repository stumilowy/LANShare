package pl.rigo.features.dashboard.presentation

import pl.rigo.features.dashboard.domain.SharedFile

data class DashboardState(
    val isServerRunning: Boolean = false,
    val lastSharedFiles: List<SharedFile> = emptyList(),
    val destinyPath: String = "",
)

package pl.rigo.features.dashboard.presentation

sealed interface DashboardAction {
    data object OnServerStart : DashboardAction

    data object OnServerStop : DashboardAction

    data class OnDestinationPathChanged(
        val path: String,
    ) : DashboardAction
}

package pl.rigo.features.dashboard.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    var state by mutableStateOf(DashboardState())

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.OnServerStart -> {
            }

            is DashboardAction.OnServerStop -> {
            }

            is DashboardAction.OnDestinationPathChanged -> {
                state = state.copy(destinyPath = action.path)
            }
        }
    }
}

package pl.rigo.features.dashboard.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.rigo.server.Server

class DashboardViewModel(
    private val server: Server,
) : ViewModel() {
    var state by mutableStateOf(DashboardState())

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.OnServerStart -> {
                CoroutineScope(Dispatchers.Default).launch {
                    server.start()
                    state = state.copy(isServerRunning = true)
                }
            }

            is DashboardAction.OnServerStop -> {
                CoroutineScope(Dispatchers.Default).launch {
                    server.stop()
                    state = state.copy(isServerRunning = false)
                }
            }

            is DashboardAction.OnDestinationPathChanged -> {
                state = state.copy(destinyPath = action.path)
            }
        }
    }
}

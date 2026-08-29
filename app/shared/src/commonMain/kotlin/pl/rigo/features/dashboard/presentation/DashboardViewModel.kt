package pl.rigo.features.dashboard.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.rigo.server.Server

class DashboardViewModel(
    private val server: Server,
) : ViewModel() {
    var state by mutableStateOf(DashboardState())
        private set

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.OnServerStart -> {
                if (state.isServerRunning || state.isServerLoading) return

                viewModelScope.launch {
                    state = state.copy(isServerLoading = true)
                    try {
                        server.start()
                        state = state.copy(isServerRunning = true, isServerLoading = false)
                    } catch (e: Exception) {
                        state = state.copy(isServerRunning = false, isServerLoading = false)
                    }
                }
            }

            is DashboardAction.OnServerStop -> {
                if (!state.isServerRunning || state.isServerLoading) return

                viewModelScope.launch {
                    try {
                        server.stop()
                        state = state.copy(isServerRunning = false, isServerLoading = false)
                    } catch (e: Exception) {
                        state = state.copy(isServerRunning = true, isServerLoading = false)
                    }
                }
            }

            is DashboardAction.OnDestinationPathChanged -> {
                state = state.copy(destinyPath = action.path)
            }
        }
    }
}

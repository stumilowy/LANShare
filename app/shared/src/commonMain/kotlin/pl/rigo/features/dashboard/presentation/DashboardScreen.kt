package pl.rigo.features.dashboard.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import pl.rigo.features.dashboard.domain.FilePicker
import pl.rigo.features.dashboard.domain.rememberFilePicker
import pl.rigo.features.dashboard.presentation.components.StandardButton
import java.nio.charset.StandardCharsets

@Composable
fun DashboardScreenRoot(viewModel: DashboardViewModel = remember { DashboardViewModel() }) {
    DashboardScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun DashboardScreen(
    state: DashboardState,
    onAction: (DashboardAction) -> Unit,
) {
    val filePicker = rememberFilePicker()

    Surface {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                StandardButton(
                    text = if (state.isServerRunning) "Stop Server" else "Start Server",
                    onClick = {
                        if (state.isServerRunning) {
                            onAction(DashboardAction.OnServerStop)
                        } else {
                            onAction(DashboardAction.OnServerStart)
                        }
                    },
                )
                StandardButton(
                    text = "Choose destination",
                    onClick = {
                        filePicker.openDirectoryPicker()?.let { path ->
                            onAction(DashboardAction.OnDestinationPathChanged(path))
                        }
                    }
                )
            }
        }
    }
}


package pl.rigo.features.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import pl.rigo.features.dashboard.domain.openDirectoryPicker
import pl.rigo.features.dashboard.presentation.components.FolderLink
import pl.rigo.features.dashboard.presentation.components.StandardButton
import pl.rigo.utils.openFolderInExplorer
import java.awt.Cursor

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
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp, 12.dp),
        ) {
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
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                StandardButton(
                    text = "Choose destination",
                    onClick = {
                        openDirectoryPicker()?.let { path ->
                            onAction(DashboardAction.OnDestinationPathChanged(path))
                        }
                    },
                )
                Row {
                    Text(
                        text = "Destination path: ",
                    )
                    FolderLink(
                        path = state.destinyPath,
                    )
                }
            }
        }
    }
}

package pl.rigo.features.dashboard.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

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
    Surface { }
}

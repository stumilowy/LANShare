package pl.rigo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import pl.rigo.core.Colours.AppDarkColorScheme
import pl.rigo.features.dashboard.presentation.DashboardScreenRoot

@Composable
fun AppTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    // TODO: add light theme
    val colorSchema = if (darkTheme) AppDarkColorScheme else AppDarkColorScheme
    MaterialTheme(
        colorScheme = colorSchema,
        content = content,
    )
}

@Composable
fun App() {
    AppTheme {
        DashboardScreenRoot()
    }
}

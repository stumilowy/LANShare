package pl.rigo.di

import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel
import pl.rigo.features.dashboard.presentation.DashboardViewModel

val shareModule =
    module {
        viewModel<DashboardViewModel>()
    }

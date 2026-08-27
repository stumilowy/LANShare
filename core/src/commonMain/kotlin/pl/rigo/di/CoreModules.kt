package pl.rigo.di

import org.koin.dsl.module
import pl.rigo.AppConfig

val coreModule =
    module {
        single<AppConfig> {
            AppConfig(
                defaultSavingDirection = "default",
            )
        }
    }

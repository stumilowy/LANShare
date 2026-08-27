package pl.rigo.di

import org.koin.dsl.module
import pl.rigo.FileServer
import pl.rigo.server.Server

val serverModule =
    module {
        includes(coreModule)
        single<Server> {
            FileServer(
                appConfig = get(),
            )
        }
    }

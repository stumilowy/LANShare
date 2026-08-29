package pl.rigo.server

import pl.rigo.AppConfig

interface Server {
    suspend fun start()

    suspend fun stop()
}

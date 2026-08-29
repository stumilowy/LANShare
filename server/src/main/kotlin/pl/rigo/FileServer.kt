package pl.rigo

import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.server.application.Application
import io.ktor.server.cio.CIO
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.copyTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import pl.rigo.server.Server
import pl.rigo.utils.isValidWindowsPath
import java.io.File

class FileServer(
    private val port: Int = 8080,
    private val host: String = "0.0.0.0",
    private val appConfig: AppConfig,
) : Server {
    private var server =
        embeddedServer(CIO, port = port, host = host) {
            configureRouting()
        }

    override suspend fun start() {
        server =
            embeddedServer(CIO, port = port, host = host) {
                configureRouting()
            }
        withContext(Dispatchers.IO) {
            if (checkConfiguration()) {
                server.start(wait = false)
            } else {
                throw IllegalArgumentException(
                    "Invalid configuration: ${appConfig.currentSavingDirection}, ${appConfig.defaultSavingDirection}",
                )
            }
        }
    }

    override suspend fun stop() {
        withContext(Dispatchers.IO) {
            server.stop(500, 700)
        }
    }

    private fun checkConfiguration(): Boolean {
        val test1 = isValidWindowsPath(appConfig.currentSavingDirection)
        val test2 = isValidWindowsPath(appConfig.defaultSavingDirection)
        return test1 and test2
    }

    private fun Application.configureRouting() {
        routing {
            get("/") {
                call.respondRedirect("/fileshare")
            }
            staticResources("/static", "static")

            staticResources("/fileshare", "static", index = "index.html")
            staticResources("/success", "static", index = "index.html")

            post("/upload") {
                val multipartData = call.receiveMultipart()
                var customName: String? = null

                multipartData.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {
                            if (part.name == "customFileName" && part.value.isNotBlank()) {
                                customName = part.value
                            }
                            part.release()
                        }

                        is PartData.FileItem -> {
                            val originalName = part.originalFileName ?: "unnamed_file"
                            val finalFileName =
                                if (customName != null) {
                                    val extension = originalName.substringAfterLast('.', "")
                                    if (extension.isNotEmpty()) "$customName.$extension" else customName
                                } else {
                                    originalName
                                }
                            val file = File(appConfig.currentSavingDirection, finalFileName)

                            val readChannel: ByteReadChannel = part.provider()
                            val writeChannel = file.writeChannel()

                            try {
                                readChannel.copyTo(writeChannel)
                            } finally {
                                writeChannel.flushAndClose()
                            }
                            part.release()
                        }

                        else -> {
                            part.release()
                        }
                    }
                }
                call.respondRedirect("/fileshare?status=success")
            }
        }
    }
}

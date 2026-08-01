package pl.rigo

import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.Netty
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.copyTo
import pl.rigo.server.Server
import pl.rigo.utils.isValidWindowsPath
import java.io.File

class FileServer(
    private val port: Int = 8080,
    private val host: String = "0.0.0.0",
    private val appConfig: AppConfig,
) : Server {
    private val server =
        embeddedServer(Netty, port = port, host = host) {
            configureRouting()
        }

    override fun start() {
        if (checkConfiguration()) {
            server.start(wait = true)
        } else {
            throw IllegalArgumentException(
                "Invalid configuration: ${appConfig.currentSavingDirection}, ${appConfig.defaultSavingDirection}",
            )
        }
    }

    override fun stop() {
        TODO("Not yet implemented")
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

            post("/upload") {
                val multipartData = call.receiveMultipart()
                multipartData.forEachPart { part ->
                    if (part is PartData.FileItem) {
                        val fileName = part.originalFileName ?: "unnamed_file"
                        val file = File(appConfig.currentSavingDirection, fileName)

                        val readChannel: ByteReadChannel = part.provider()
                        val writeChannel = file.writeChannel()

                        readChannel.copyTo(writeChannel)
                    }
                    part.release()
                }
            }
        }
    }
}

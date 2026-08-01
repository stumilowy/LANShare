package pl.rigo

fun main() {
    val server =
        FileServer(
            port = 8080,
            host = "0.0.0.0",
            appConfig =
                AppConfig(
                    defaultSavingDirection = "C:\\Users\\Public\\Downloads",
                ),
        )
    server.start(8080, "0.0.0.0")
}

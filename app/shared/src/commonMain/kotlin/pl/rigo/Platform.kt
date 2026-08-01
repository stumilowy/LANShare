package pl.rigo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
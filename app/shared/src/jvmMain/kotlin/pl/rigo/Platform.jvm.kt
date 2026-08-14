package pl.rigo

import pl.rigo.features.dashboard.presentation.DashboardState

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

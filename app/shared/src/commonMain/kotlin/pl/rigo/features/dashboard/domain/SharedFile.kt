package pl.rigo.features.dashboard.domain

class SharedFile(
    val name: String,
    val extension: String,
    val type: FileType,
    val size: Long,
    val path: String,
)

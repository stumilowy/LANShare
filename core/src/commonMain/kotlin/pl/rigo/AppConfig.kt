package pl.rigo

data class AppConfig(
    val defaultSavingDirection: String,
    val currentSavingDirection: String = defaultSavingDirection,
)

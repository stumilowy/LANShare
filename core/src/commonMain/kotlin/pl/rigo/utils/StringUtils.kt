package pl.rigo.utils

import java.nio.file.InvalidPathException
import java.nio.file.Paths

fun isValidWindowsPath(pathStr: String): Boolean =
    try {
        Paths.get(pathStr)
        true
    } catch (e: InvalidPathException) {
        false
    }

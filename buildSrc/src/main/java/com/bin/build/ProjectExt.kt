package com.bin.build

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

private var _energyNotesocalProperties: Properties? = null

/**
 * Reads the `energy-notes-local.properties` configuration from the root project if there is any
 */
val Project.energyNotesocalProperties: Properties
    get() = _energyNotesocalProperties ?: readProperties("energy-notes-local.properties")
        .also { _energyNotesocalProperties = it }

private fun Project.readProperties(file: String): Properties {
    val properties = Properties()
    val propertiesFile = File(rootProject.projectDir, file)
    if (propertiesFile.isFile) {
        InputStreamReader(FileInputStream(propertiesFile), Charsets.UTF_8)
            .use(properties::load)
    }
    return properties
}

fun Project.isRootProject() = rootProject == this

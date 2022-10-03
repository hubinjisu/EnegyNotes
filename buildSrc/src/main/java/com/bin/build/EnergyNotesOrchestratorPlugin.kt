/*
 * Copyright (c) 2022, DB Vertrieb GmbH, Mobiles Terminal
 */package com.bin.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.closureOf

@Suppress("DefaultLocale", "unused")
class EnergyNotesOrchestratorPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.configure(project, closureOf<Project> {
            // This is registered at the very beginning, so plugins can depend on it
            if (isRootProject()) {
                configureRootProject()
            } else {
                configureSubmoduleProject()
            }
        })
    }

    private fun Project.configureRootProject() {
        group = PROJECT_GROUP
        version = "Energy-Notes-1.0.0"
        extensions.extraProperties["versionCode"] = "1.0.0"
    }

    private fun Project.configureSubmoduleProject() {
        version = rootProject.version
    }

    companion object {
        private const val PROJECT_GROUP = "de.db.mt"
        private val CPD_IGNORED_PROJECTS = listOf<String>()
    }
}

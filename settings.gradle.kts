@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
rootProject.name = "EnergyNotes"
include(":app")
include(":ui")
include(":presentation")
include(":data")
include(":domain")

rootProject.children.forEach { subproject ->
    subproject.buildFileName = "${subproject.name}.gradle.kts"
}

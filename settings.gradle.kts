@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "EnergyNotes"
include(":app")
include(":ui")
include(":presentation")
include(":data")

rootProject.children.forEach { subproject ->
    subproject.buildFileName = "${subproject.name}.gradle.kts"
}

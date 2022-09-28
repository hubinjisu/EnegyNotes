package com.bin.build

object EnergyNotes {
    const val groupId = "com.bin.app"
    const val artifactId = "energyNotes"
}

object Projects {
    const val app = ":app"
    const val data = ":data"
    const val presentation = ":presentation"
    const val ui = ":ui"
}

object Artifactory {
    private const val url = "https://github.com/hubinjisu/"
    const val energyNotes = url + "EnergyNotes/"
}

object AndroidSdk {
    const val minSdkVersion = 28
    const val compileSdkVersion = 32
    const val targetSdkVersion = 32

    // Keep in sync with Dockerfile's ADDITIONAL_PLATFORMS
    const val ndkVersion = "21.3.6528147"

    // Keep in sync with Dockerfile's BUILD_TOOLS
    const val buildToolsVersion = "33.0.0"
}
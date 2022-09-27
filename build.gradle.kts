buildscript {
    extra.apply {
        set("compose_version", "1.2.0-rc02")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
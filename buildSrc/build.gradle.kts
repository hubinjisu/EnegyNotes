plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

val kotlinVersion = "1.6.21"

configurations.configureEach {
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    }
}

dependencies {
    implementation(gradleApi())
    // See comments in Dependencies.kt to find all the places this needs to be changed
    implementation("com.android.tools.build:gradle:7.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}
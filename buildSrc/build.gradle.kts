import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
}

val kotlinVersion = "1.6.21"

configurations.configureEach {
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    }
}

gradlePlugin {
    plugins {
        register("energyNotesOrchestratorPlugin") {
            id = "energyNotes.orchestrator"
            implementationClass = "com.bin.build.EnergyNotesOrchestratorPlugin"
        }
    }
}

tasks.withType<KotlinCompile>()
    .configureEach {
        kotlinOptions { jvmTarget = JavaVersion.VERSION_11.toString() }
    }

dependencies {
    implementation(gradleApi())
    // See comments in Dependencies.kt to find all the places this needs to be changed
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("com.squareup:javapoet:1.13.0")

//    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.5")
}
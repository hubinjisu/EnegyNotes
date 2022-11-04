import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id(Plugins.daggerHilt) version Libraries.Dagger.version apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
//        classpath(Libraries.Android.gradlePlugin)
//        classpath(Libraries.Kotlin.gradlePlugin)
//        classpath(Libraries.AndroidX.Navigation.navigationSafeArgsGradlePlugin)
        classpath(Libraries.Dagger.hiltAndroidGradlePlugin)
        classpath("com.google.gms:google-services:4.3.14")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.withType(Test::class.java).configureEach {
    useJUnitPlatform()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    tasks.withType<Test>().configureEach {
        failFast = true

        // courtesy of https://stackoverflow.com/a/36130467/305532
        testLogging {
            events(TestLogEvent.FAILED)
            exceptionFormat = TestExceptionFormat.SHORT
            showExceptions = true
            showCauses = true
            showStackTraces = true

            debug {
                events(
                    TestLogEvent.STARTED,
                    TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.STANDARD_ERROR,
                    TestLogEvent.STANDARD_OUT
                )
                exceptionFormat = TestExceptionFormat.FULL
            }
            info.events = debug.events
            info.exceptionFormat = debug.exceptionFormat

            fun <T, U> Any.binaryClosureOf(action: (T, U) -> Unit): groovy.lang.Closure<Any?> =
                KotlinClosure2(action, this, this)

            afterSuite(
                binaryClosureOf<TestDescriptor, TestResult> { desc, result ->
                    desc.parent?.let { // will match the outermost suite
                        with(result) {
                            @Suppress("UnstableApiUsage")
                            logger.lifecycle(
                                "${desc.displayName}: $resultType " +
                                        "($testCount tests, $successfulTestCount passed, " +
                                        "$failedTestCount failed, $skippedTestCount skipped)"
                            )
                        }
                    }
                }
            )
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            allWarningsAsErrors = true
            // treat nullability warnings with Java APIs as errors
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
}
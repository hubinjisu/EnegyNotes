plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id("jacoco")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 28
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/src/test/resources/migrations"
                )
            }
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
            all { test ->
                test.systemProperty(
                    "user.home",
                    System.getenv("WORKSPACE") ?: System.getenv("HOME")
                )

                test.configure<JacocoTaskExtension> {
                    isIncludeNoLocationClasses = true
                    excludes = listOf("jdk.internal.*")
                }
            }
        }
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("META-INF/*.md")
            excludes.add("META-INF/*.kotlin_module")
            pickFirsts.add("LICENSE-EDL-1.0.txt")
            pickFirsts.add("LICENSE-EPL-1.0.txt")
            pickFirsts.add("META-INF/io.netty.versions.properties")
            merges.add("META-INF/INDEX.LIST")
        }
    }
}
//
//tasks.withType(Test::class.java).configureEach {
//    useJUnitPlatform()
//}

//configurations.configureEach {
//    resolutionStrategy.eachDependency {
//        if (requested.group == "org.jetbrains.kotlin" && requested.name == "kotlin-stdlib") {
//            useVersion("1.6.21")
//        }
//    }
//}

dependencies {
    implementation(project(Projects.domain))

//    implementation(Libraries.javaxAnnotation)
//    api(Libraries.Jackson.jakartaAnnotations)
    implementation(Libraries.AndroidX.annotation)
    implementation(Libraries.Kotlin.stdlib)

    implementation(Libraries.javaxInject)
    api(Libraries.Dagger.dagger)
    kapt(Libraries.Dagger.daggerCompiler)
    // Room components
    implementation(Libraries.AndroidX.Room.roomRuntime)
    implementation(Libraries.AndroidX.Room.roomMigration)
    implementation(Libraries.AndroidX.Room.roomRxJava)
    kapt(Libraries.AndroidX.Room.roomCompiler)
    kapt(Libraries.AndroidX.Room.roomKotlin)
    kapt(Libraries.Dagger.androidProcessor)
    kapt(Libraries.Dagger.compiler)
    implementation(Libraries.Jackson.kotlin)
    implementation(Libraries.Dagger.hiltCore)
    implementation(Libraries.Dagger.hiltAndroid)
    kapt(Libraries.Dagger.hiltAndroidCompiler)
    kapt(Libraries.AndroidX.Hilt.hiltCompiler)
//    implementation(Libraries.Moshi.kotlin) {
//        excludeKotlinReflect()
//    }
    implementation(Libraries.timber)
//    implementation(Libraries.Moshi.adapters)
//    kapt(Libraries.Moshi.kotlinCodegen)
    implementation ("androidx.room:room-ktx:2.4.1")
    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

    testImplementation(Libraries.JUnit.jUnit)
    androidTestImplementation(Libraries.AndroidX.Test.extJunit)
    androidTestImplementation(Libraries.AndroidX.ArchCore.coreTesting)
    androidTestImplementation(Libraries.AndroidX.Test.core)
    androidTestImplementation(Libraries.AndroidX.Test.runner)
//    androidTestImplementation(Libraries.AndroidX.Room.roomTesting)
    androidTestImplementation(Libraries.AndroidX.Test.extTruth)
    androidTestImplementation( "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")

    kaptTest(Libraries.AndroidX.Room.roomCompiler)
//    testImplementation(Libraries.JUnit.Jupiter.junitJupiterApi)
//    testRuntimeOnly(Libraries.JUnit.Jupiter.junitJupiterEngine)
//    testRuntimeOnly(Libraries.JUnit.Jupiter.junitVintageEngine)
//    testImplementation(Libraries.Mockito.core)
//    testImplementation(Libraries.Mockito.Support.kotlin)
//    testImplementation(Libraries.Robolectric.robolectric)
    androidTestImplementation(Libraries.Robolectric.robolectric) {
        exclude(module = "auto-service")
    }
}
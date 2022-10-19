plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
}

android {
    namespace = "com.bin.domain"
    compileSdk = 32

    defaultConfig {
        minSdk = 28
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    api(Libraries.Dagger.dagger)
    kapt(Libraries.Dagger.daggerCompiler)
    kapt(Libraries.Dagger.androidProcessor)

//    implementation(Libraries.javaxAnnotation)
    implementation(Libraries.javaxInject)
    api(Libraries.Jackson.jakartaAnnotations)
    implementation(Libraries.Jackson.kotlin)
    implementation(Libraries.AndroidX.annotation)
    implementation(Libraries.Kotlin.stdlib)

    implementation(Libraries.Dagger.hiltCore)
    implementation(Libraries.Dagger.hiltAndroid)
    kapt(Libraries.Dagger.hiltAndroidCompiler)
    kapt(Libraries.AndroidX.Hilt.hiltCompiler)

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

    testImplementation(Libraries.JUnit.jUnit)
    testImplementation(Libraries.AndroidX.Test.extJunit)
}
import com.bin.build.Libraries.AndroidX
import com.bin.build.Libraries.Dagger
import com.bin.build.Libraries.timber

plugins {
//    id(com.bin.build.Plugins.androidLibrary)
//    kotlin(Plugins.android)
//    kotlin(Plugins.kapt)
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
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
}

dependencies {

    implementation(AndroidX.Core.coreKtx)
    implementation(AndroidX.Lifecycle.lifecycleViewmodelCompose)
    api(Dagger.hiltCore)
    api(Dagger.hiltAndroid)
    implementation(timber)
    kapt(Dagger.hiltAndroidCompiler)
    kapt(AndroidX.Hilt.hiltCompiler)
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
}
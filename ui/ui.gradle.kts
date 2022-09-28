import com.bin.build.Libraries.AndroidX
import com.bin.build.Libraries.JUnit
import com.bin.build.Plugins
import com.bin.build.Projects

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
//    id(Plugins.androidLibrary)
//    kotlin(Plugins.android)
//    kotlin(Plugins.kapt)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 28
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
        dataBinding = true
        resValues = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidX.Compose.compilerVersion
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(Projects.presentation))
    implementation(AndroidX.Core.coreKtx)
    implementation(AndroidX.Compose.Ui.ui)
    implementation(AndroidX.Compose.Ui.uiToolingPreview)
    implementation(AndroidX.Compose.Ui.uiTestJunit4)
    implementation(AndroidX.Compose.Material.material)
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation(AndroidX.Activity.activityCompose)
    testImplementation(JUnit.jUnit)
    testImplementation(AndroidX.Test.extJunit)
    testImplementation(AndroidX.espresso)
    androidTestImplementation(AndroidX.Compose.Ui.uiTestJunit4)
    debugImplementation(AndroidX.Compose.Ui.uiTooling)
}
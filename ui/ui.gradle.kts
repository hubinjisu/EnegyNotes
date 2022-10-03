plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
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
        kotlinCompilerExtensionVersion = Libraries.AndroidX.Compose.compilerVersion
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(Projects.presentation))
    implementation(Libraries.AndroidX.Core.coreKtx)
    implementation(Libraries.AndroidX.Compose.Ui.ui)
    implementation(Libraries.AndroidX.Compose.Ui.uiToolingPreview)
    implementation(Libraries.AndroidX.Compose.Ui.uiTestJunit4)
    implementation(Libraries.AndroidX.Compose.Material.material)

    api(Libraries.Dagger.hiltAndroid)
    implementation(Libraries.AndroidX.Hilt.hiltNavigationCompose)
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation(Libraries.AndroidX.Activity.activityCompose)
    testImplementation(Libraries.JUnit.jUnit)
    testImplementation(Libraries.AndroidX.Test.extJunit)
    testImplementation(Libraries.AndroidX.espresso)
    androidTestImplementation(Libraries.AndroidX.Compose.Ui.uiTestJunit4)
    debugImplementation(Libraries.AndroidX.Compose.Ui.uiTooling)
}
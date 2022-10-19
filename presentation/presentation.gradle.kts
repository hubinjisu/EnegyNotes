plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id(Plugins.daggerHilt)
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
    implementation(project(Projects.domain))

    implementation(Libraries.AndroidX.Lifecycle.lifecycleViewmodelCompose)
    api(Libraries.Dagger.hiltCore)
    api(Libraries.Dagger.hiltAndroid)
    implementation(Libraries.timber)
    kapt(Libraries.Dagger.hiltAndroidCompiler)
    kapt(Libraries.AndroidX.Hilt.hiltCompiler)
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")

    implementation(Libraries.material)
    implementation(Libraries.AndroidX.Core.coreKtx)
    implementation(Libraries.AndroidX.Compose.Ui.ui)
    implementation(Libraries.AndroidX.Compose.Ui.uiToolingPreview)
    implementation(Libraries.AndroidX.Compose.Ui.uiTestJunit4)
    implementation(Libraries.AndroidX.Compose.Material.materialIconsCore)
    api(Libraries.AndroidX.Compose.Material.material)
    api(Libraries.Dagger.dagger)
    implementation(Libraries.Dagger.hiltCore)
    implementation(Libraries.Dagger.hiltAndroid)
    kapt(Libraries.Dagger.hiltAndroidCompiler)
    kapt(Libraries.AndroidX.Hilt.hiltCompiler)
    kapt(Libraries.Dagger.daggerCompiler)
    kapt(Libraries.AndroidX.Lifecycle.lifecycleCompiler)
    implementation(Libraries.AndroidX.Hilt.hiltNavigationCompose)
    implementation(Libraries.AndroidX.Activity.activityCompose)

}

kapt {
    correctErrorTypes= true
}
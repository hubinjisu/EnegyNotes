import com.bin.build.Libraries.AndroidX
import com.bin.build.Plugins

plugins {
//    id(Plugins.androidLibrary)
//    kotlin(Plugins.android)
//    kotlin(Plugins.kapt)
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
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
//    implementation("androidx.activity:activity-ktx:1.2.3")
//    implementation("androidx.fragment:fragment-ktx:1.3.2")
//    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.2.0")
    implementation (AndroidX.Lifecycle.lifecycleViewmodelCompose)
//    implementation("com.google.android.material:material:1.6.1")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
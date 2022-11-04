plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id(Plugins.daggerHilt)
    id(Plugins.googleServices)
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.bin.app"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

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

dependencies {
    api(project(Projects.ui))
//    api(project(Projects.presentation))
//    api(project(Projects.domain))
    api(project(Projects.data))

    implementation(platform(Libraries.Firebase.firebaseBom))
    implementation(Libraries.Firebase.databaseKtx)
    implementation(Libraries.timber)
    kapt(Libraries.Dagger.androidProcessor)
    kapt(Libraries.Dagger.compiler)
    api(Libraries.AndroidX.appCompat)
    implementation(Libraries.composeThemeAdapter)
    implementation(Libraries.material)
    implementation(Libraries.AndroidX.Core.coreKtx)
    implementation(Libraries.AndroidX.Compose.Ui.ui)
    implementation(Libraries.AndroidX.Compose.Ui.uiToolingPreview)
    implementation(Libraries.AndroidX.Compose.Ui.uiTestJunit4)
    implementation(Libraries.AndroidX.Compose.Material.materialIconsCore)
    api(Libraries.AndroidX.Compose.Material.material)
    api(Libraries.Dagger.dagger)
    implementation(Libraries.Dagger.android)
    implementation(Libraries.Dagger.androidSupport)
    implementation(Libraries.Dagger.hiltCore)
    implementation(Libraries.Dagger.hiltAndroid)
    kapt(Libraries.Dagger.hiltAndroidCompiler)
    kapt(Libraries.AndroidX.Hilt.hiltCompiler)
    kapt(Libraries.Dagger.daggerCompiler)
    kapt(Libraries.AndroidX.Lifecycle.lifecycleCompiler)
    implementation(Libraries.AndroidX.Hilt.hiltNavigationCompose)
    implementation(Libraries.AndroidX.Activity.activityCompose)
}

//hilt {
//    enableAggregatingTask = false
//}

kapt {
    correctErrorTypes= true
}
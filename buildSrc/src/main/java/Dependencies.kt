import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ModuleIdentifier
import org.gradle.kotlin.dsl.exclude

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val android = "android"
    const val jvm = "jvm"
    const val kapt = "kapt"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"

    const val testFixtures = "java-test-fixtures"

    const val navigationSafeargs = "androidx.navigation.safeargs.kotlin"
    const val kotlinParcelize = "kotlin-parcelize"

    object Versions {
        const val version = "0.38.0"
        const val id = "com.github.ben-manes.versions"
    }

    object DependencyGraphGenerator {
        const val version = "0.5.0"
        const val id = "com.vanniktech.dependency.graph.generator"
    }

    object BuildTimeTracker {
        const val version = "4.3.0"
        const val id = "com.asarkar.gradle.build-time-tracker"
    }

    object Firebase {
        const val crashlyticsPlugin = "com.google.firebase.crashlytics"
    }

    object DependencyAnalysis {
        const val version = "1.4.0"
        const val id = "com.autonomousapps.dependency-analysis"
    }
}

object Libraries {

    object Kotlin {
        @Suppress("MemberVisibilityCanBePrivate")
        const val version = "1.6.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val allOpen = "org.jetbrains.kotlin:kotlin-allopen:$version"

        // Reflection ist nur in Tests erlaubt und soll nicht im produktiven Code benutzt werden
        // ...aber es gelangt dennoch transitiv durch andere Dependencies in den Classpath :(
        const val reflectForTestsOnly = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object Android {
        const val gradlePluginVersion = "7.2.1" // also changes Databinding Compiler version
        const val gradlePlugin = "com.android.tools.build:gradle:$gradlePluginVersion"
    }

    object AndroidX {

        object Compose {
            // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
            const val compilerVersion = "1.2.0-rc02"
            private const val version = "1.1.1"

            object Runtime {
                const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
                const val runtime = "androidx.compose.runtime:runtime:$version"
            }

            object Foundation {
                const val foundation = "androidx.compose.foundation:foundation:$version"
                const val foundationLayout = "androidx.compose.foundation:foundation-layout:$version"
            }

            object Ui {
                const val ui = "androidx.compose.ui:ui:$version"
                const val uiGraphics = "androidx.compose.ui:ui-graphics:$version"
                const val uiText = "androidx.compose.ui:ui-text:$version"
                const val uiUnit = "androidx.compose.ui:ui-unit:$version"
                const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
                const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
                const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"
            }

            object Material {
                const val material = "androidx.compose.material:material:$version"
                const val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
            }
        }

        object Databinding {
            private const val version = "7.2.0" // follows AGP versions
            const val compiler = "androidx.databinding:databinding-compiler:$version"
        }

        object Activity {
            private const val version = "1.5.0"
            const val activity = "androidx.activity:activity:$version"
            const val activityCompose = "androidx.activity:activity-compose:$version"
        }

        object Core {
            private const val version = "1.6.0"
            const val core = "androidx.core:core:$version"
            const val coreKtx = "androidx.core:core-ktx:$version"
        }

        object ArchCore {
            private const val version = "2.1.0"
            const val coreCommon = "androidx.arch.core:core-common:$version"
            const val coreTesting = "androidx.arch.core:core-testing:$version"
        }

        object Room {
            private const val version = "2.4.2"
            const val roomCompiler = "androidx.room:room-compiler:$version"
            const val roomRuntime = "androidx.room:room-runtime:$version"
            const val roomMigration = "androidx.room:room-migration:$version"
            const val roomTesting = "androidx.room:room-testing:$version"
            const val roomRxJava = "androidx.room:room-rxjava3:$version"
            const val roomCommon = "androidx.room:room-common:$version"
        }

        object Sqlite {
            const val sqlite = "androidx.sqlite:sqlite:2.1.0"
        }

        private const val annotationVersion = "1.2.0"
        const val annotation = "androidx.annotation:annotation:$annotationVersion"

        private const val constraintLayoutVersion = "2.0.4"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

        private const val appCompatVersion = "1.3.0"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

        private const val swipeRefreshLayoutVersion = "1.1.0"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion"

        // Siehe auch https://github.com/robolectric/robolectric/issues/6593
        private const val espressoVersion = "3.5.0-alpha03"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:$espressoVersion"
        const val espressoIntents = "androidx.test.espresso:espresso-intents:$espressoVersion"
        const val espressoWeb = "androidx.test.espresso:espresso-web:$espressoVersion"

        object Fragment {
            private const val version = "1.3.2"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
            const val fragmentTesting = "androidx.fragment:fragment-testing:$version"
        }

        object Lifecycle {
            private const val version = "2.5.0"
            const val lifecycleLivedataCore = "androidx.lifecycle:lifecycle-livedata-core:$version"
            const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel:$version"
            const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val lifecycleViewmodelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            const val lifecycleViewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:$version"
            const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$version"
        }

        object Navigation {
            private const val version = "2.5.0"
            const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
            const val navigationCommon = "androidx.navigation:navigation-common:$version"
            const val navigationCompose = "androidx.navigation:navigation-compose:$version"
            const val navigationRuntime = "androidx.navigation:navigation-runtime:$version"
        }

        object Hilt {
            private const val version = "1.0.0"
            const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$version"
            const val hiltCompiler = "androidx.hilt:hilt-compiler:$version"
        }

        private const val preferenceVersion = "1.1.1"
        const val preference = "androidx.preference:preference:$preferenceVersion"

        object Test {
            @Suppress("MemberVisibilityCanBePrivate")
            const val version = "1.4.1-alpha07"
            const val truthVersion = "1.5.0-alpha07"
            const val core = "androidx.test:core:$version"
            const val orchestrator = "androidx.test:orchestrator:1.4.1"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"
            const val extTruth = "androidx.test.ext:truth:$truthVersion"
            const val extJunit = "androidx.test.ext:junit:1.1.2"
        }

        private const val workmanagerVersion = "2.7.1"
        const val workmanagerRuntime = "androidx.work:work-runtime-ktx:$workmanagerVersion"
        const val workmanagerRxJava = "androidx.work:work-rxjava3:$workmanagerVersion"
        const val workmanagerTest = "androidx.work:work-testing:$workmanagerVersion"

        private const val securityVersion = "1.0.0"
        const val securityCrypto = "androidx.security:security-crypto:$securityVersion"

        private const val webkitVersion = "1.4.0"
        const val webkit = "androidx.webkit:webkit:$webkitVersion"

        private const val activityVersion = "1.2.3"
        const val activity = "androidx.activity:activity-ktx:$activityVersion"

        private const val camerax_version = "1.0.0"
        const val camerax_camera2 = "androidx.camera:camera-camera2:${camerax_version}"
        const val camerax_lifecycle = "androidx.camera:camera-lifecycle:${camerax_version}"
        const val camerax_view = "androidx.camera:camera-view:1.0.0-alpha25"
    }

    private const val adobeAnalyticsMobileVersion = "4.18.2"
    const val adobeAnalyticsMobile = "com.adobe.analytics:adobe-mobile-library:$adobeAnalyticsMobileVersion"

    private const val apacheCommonsCodecVersion = "1.15"
    const val apacheCommonsCodec = "commons-codec:commons-codec:$apacheCommonsCodecVersion"

    private const val apacheCommonsIOVersion = "2.8.0"
    const val apacheCommonsIO = "commons-io:commons-io:$apacheCommonsIOVersion"

    private const val awaitilityVersion = "4.1.0"
    const val awaitilityKotlin = "org.awaitility:awaitility-kotlin:$awaitilityVersion"

    object Dagger {
        private const val version = "2.42"
        const val dagger = "com.google.dagger:dagger:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val android = "com.google.dagger:dagger-android:$version"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"

        const val hiltCore = "com.google.dagger:hilt-core:$version"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    private const val easyValidationVersion = "1.0.1"
    const val easyValidation = "com.wajahatkarim3.easyvalidation:easyvalidation-core:$easyValidationVersion"

    private const val fileloggerVersion = "1.0.7"
    const val filelogger = "com.github.bosphere.android-filelogger:filelogger:$fileloggerVersion"

    private const val flexboxVersion = "3.0.0"
    const val flexbox = "com.google.android.flexbox:flexbox:$flexboxVersion"

    const val javaxAnnotation = "javax.annotation:jsr250-api:1.0"
    const val javaxInject = "javax.inject:javax.inject:1"

    object JUnit {
        private const val jUnitVersion = "4.13.2"
        const val jUnit = "junit:junit:$jUnitVersion"

        private const val jupiterVersion = "5.7.1"
        const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:$jupiterVersion"
        const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:$jupiterVersion"
        const val vintageEngine = "org.junit.vintage:junit-vintage-engine:$jupiterVersion"
        const val jupiterParams = "org.junit.jupiter:junit-jupiter-params:$jupiterVersion"
    }

    private const val kotestVersion = "4.1.3"
    const val kotest = "io.kotest:kotest-core:$kotestVersion"

    const val ktlintVersion = "0.36.0"

    private const val leakcanaryVersion = "2.8.1"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:$leakcanaryVersion"

    private const val materialVersion = "1.3.0"
    const val material = "com.google.android.material:material:$materialVersion"

    object Mockito {
        private const val version = "3.11.2"
        const val core = "org.mockito:mockito-core:$version"
        const val android = "org.mockito:mockito-android:$version"
        const val inline = "org.mockito:mockito-inline:$version"

        object Support {
            private const val version = "2.2.0"
            const val kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$version"
        }
    }

    private const val mtxCommonAbrechnungVersion = "9.0.14"
    const val mtxCommonAbrechnung = "de.db.mtx.common:abrechnung:$mtxCommonAbrechnungVersion"

    private const val anrWatchdogVersion = "1.4.0"
    const val anrWatchdog = "com.github.anrwatchdog:anrwatchdog:$anrWatchdogVersion"

    object Jackson {
        private const val version = "2.13.2"
        private const val databindVersion = "2.13.2.1"
        const val annotations = "com.fasterxml.jackson.core:jackson-annotations:$version"
        const val bind = "com.fasterxml.jackson.core:jackson-databind:$databindVersion"
        const val jaxb = "com.fasterxml.jackson.module:jackson-module-jaxb-annotations:$version"
        const val kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:$version"
        const val xml = "com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$version"
        const val datatypeJsr310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$version"
        const val jakartaAnnotations =
            "com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations:2.13.0-rc1"
    }

    object Jaxb {
        private const val version = "3.0.1"
        const val api = "jakarta.xml.bind:jakarta.xml.bind-api:$version"
        const val core = "com.sun.xml.bind:jaxb-xjc:$version"
        const val impl = "com.sun.xml.bind:jaxb-impl:$version"
        const val xjc = "com.sun.xml.bind:jaxb-xjc:$version"
        const val osgi = "com.sun.xml.bind:jaxb-osgi:$version"
        const val apiForTest = "javax.xml.bind:jaxb-api:2.3.1"
        const val istack = "com.sun.istack:istack-commons-runtime:3.0.11"
    }

    object Moshi {
        private const val version = "1.13.0"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val kotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
        const val adapters = "com.squareup.moshi:moshi-adapters:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
        const val adapterRxjava = "com.squareup.retrofit2:adapter-rxjava3:$version"
    }

    object OkHttp {
        private const val version = "4.9.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Robolectric {
        private const val robolectricVersion = "4.8"
        private const val robolectricConfigurationVersion = "4.8"
        const val robolectric = "org.robolectric:robolectric:$robolectricVersion"
        const val annotations = "org.robolectric:annotations:$robolectricVersion"

        object Support {
            const val config = "de.db.mt.test:robolectric-configuration:$robolectricConfigurationVersion"
        }
    }

    object RxJava {
        private const val rxJavaVersion = "3.1.4"
        const val rxJava = "io.reactivex.rxjava3:rxjava:$rxJavaVersion"
        private const val rxAndroidVersion = "3.0.0"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:$rxAndroidVersion"
        private const val rxKotlinVersion = "3.0.1"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:$rxKotlinVersion"

        object Support {
            private const val rxBroadcastReceiverVersion = "1.0.6"
            const val rxBroadcastReceiver = "com.github.karczews:rx2-broadcast-receiver:$rxBroadcastReceiverVersion"
        }
    }

    private const val slf4jVersion = "1.7.31"
    const val slf4j = "org.slf4j:log4j-over-slf4j:$slf4jVersion"

    private const val slf4jTimberVersion = "3.1"
    const val slf4jTimber = "com.arcao:slf4j-timber:$slf4jTimberVersion"

    private const val staxApiVersion = "1.0.1"
    const val staxApi = "stax:stax-api:$staxApiVersion"

    private const val processPhoenixVersion = "2.1.2"
    const val processPhoenix = "com.jakewharton:process-phoenix:$processPhoenixVersion"

    private const val timberVersion = "5.0.1"
    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    private const val timberStubVersion = "1.0.1"
    const val timberStub = "de.db.mt:timber-stub:$timberStubVersion"

    private const val wifiUtilsVersion = "1.6.6"
    const val wifiUtils = "io.github.thanosfisherman.wifiutils:wifiutils:$wifiUtilsVersion"

    const val truth = "com.google.truth:truth:1.1.2"

    private const val secureKeyImportVersion = "1.0.6"
    const val secureKeyImport = "de.db.mt:secure-key-import:$secureKeyImportVersion"

    object SpongyCastle {
        private const val version = "1.53.0.0"
        const val core = "com.madgag.spongycastle:core:$version"
        const val prov = "com.madgag.spongycastle:prov:$version"
    }

    object BouncyCastle {
        private const val version = "1.66"
        const val prov = "org.bouncycastle:bcprov-jdk15on:$version"
        const val pg = "org.bouncycastle:bcpg-jdk15on:$version"
        const val pkix = "org.bouncycastle:bcpkix-jdk15on:$version"
    }

    object Firebase {
        const val firebaseBom = "com.google.firebase:firebase-bom:29.0.0"
        const val crashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
        const val messagingKtx = "com.google.firebase:firebase-messaging-ktx"
    }

    object Scanbot {
        // https://nexus.scanbot.io/nexus/content/repositories/releases/io/scanbot/
        private const val version = "1.92.0"
        const val scanBot = "io.scanbot:sdk-package-4:$version"
        const val scanBotGenericDocumentAssets = "io.scanbot:sdk-genericdocument-assets:$version"
        const val scanBotEhicAssets = "io.scanbot:sdk-ehic-assets:$version"
    }

    object hive {
        private const val version = "1.3.0"
        const val clientMqtt = "com.hivemq:hivemq-mqtt-client:$version"
    }

    private const val zxingCoreVersion = "3.4.1"
    const val zxingCore = "com.google.zxing:core:$zxingCoreVersion"

    private const val googleGsonVersion = "2.8.9"
    const val googleGson = "com.google.code.gson:gson:$googleGsonVersion"
}

object ExcludedFromUpdates {
    private val dependencies = listOf(
        "com.madgag.spongycastle:core",
        "com.madgag.spongycastle:prov"
    )

    fun contains(module: ModuleIdentifier) = dependencies.contains("${module.group}:${module.name}")
}

/**
 * This is pulled in by moshi-kotlin and jackson-module-kotlin in various places, but we don't want this in the runtime
 * and moshi can make use of code generation anyways
 *
 * We cannot globally exclude this via configurations.all {} since this would remove valid instances
 * that are needed e.g. for test compilation as well
 */
fun ExternalModuleDependency.excludeKotlinReflect() {
    exclude(group = "org.jetbrains.kotlin", module = "kotlin-reflect")
}

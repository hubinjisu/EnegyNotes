
//buildscript {
//    dependencies {
//        classpath(Android.gradlePlugin)
//        classpath(Kotlin.gradlePlugin)
//        classpath(AndroidX.Navigation.navigationSafeArgsGradlePlugin)
//        classpath(Dagger.hiltAndroidGradlePlugin)
//    }
//}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
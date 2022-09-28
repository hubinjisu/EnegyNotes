import com.bin.build.Libraries.Dagger
import com.bin.build.Libraries.AndroidX
import com.bin.build.Libraries.Kotlin
import com.bin.build.Libraries.Android

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
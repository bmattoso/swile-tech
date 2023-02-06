import com.android.build.gradle.BaseExtension
import com.android.build.gradle.BasePlugin

@Suppress("DSL_SCOPE_VIOLATION") // FIXME: Remove once KTIJ-19369 and  is fixed

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.lint) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.gms.googleServices) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kover)
}

allprojects {
    apply(plugin = rootProject.libs.plugins.kover.get().pluginId)
}

subprojects {
    plugins.withType<BasePlugin>().configureEach {
        extensions.configure<BaseExtension> {
            compileSdkVersion(libs.versions.compileSdk.get().toInt())
            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
                targetSdk = libs.versions.targetSdk.get().toInt()
            }
        }
    }
}

koverMerged {
    enable()

    filters {
        classes {
            excludes.addAll(
                listOf("**BuildConfig**")
            )
        }
        annotations {
            excludes.addAll(listOf("androidx.compose.runtime.Composable"))
        }
    }
}
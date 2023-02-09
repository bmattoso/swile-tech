@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.ksp)
}

android {

    defaultConfig {
        applicationId = "com.br.swile.tech"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = ".debug"
        }

        val release by getting {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )

        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
    }
    packagingOptions {
        packagingOptions.resources.excludes += setOf(
            "**/attach_hotspot_windows.dll",
            "META-INF/AL2.0",
            "META-INF/LGPL2.1",
            "META-INF/licenses/ASM"
        )
    }

    namespace = "com.br.swile.tech"
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableTransformForLocalTests = true
}

dependencies {

    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    implementation(libs.accompanist.uicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.coil.coil)
    implementation(libs.coil.compose)
    implementation(libs.compose.material.material.dynamic)
    implementation(libs.compose.ui.ui)
    implementation(libs.google.devtools.ksp)
    implementation(libs.hilt)
    implementation(libs.hilt.compose.navigation)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.datetime)
    implementation(libs.lottie)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.kotlin.serialization)

    kapt(libs.hilt.compiler)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.manifest)

    testImplementation(libs.androidx.archCoreTesting)
    testImplementation(libs.androidx.room.testing)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.hilt.testing)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlinx.datetime)
    testImplementation(libs.mockK)
    testImplementation(libs.robolectric)

    kspTest(libs.androidx.room.compiler)
    kaptTest(libs.hilt.compiler)

    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.compose.ui.test)
}
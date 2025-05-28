plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

android {
    namespace = "com.parcial.tp3"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.parcial.tp3"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }
}

dependencies {

    // Retrofit (cliente HTTP)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Converter para trabajar con JSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Cliente HTTP OkHttp (base)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // OkHttp Logging para ver peticiones/respuestas en Logcat
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.runtime.android)
    kapt("com.google.dagger:hilt-compiler:2.48")

    // Hilt para ViewModel
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
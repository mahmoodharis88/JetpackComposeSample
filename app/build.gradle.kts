plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.sample.jetpackcomposesample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sample.jetpackcomposesample"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"

        }

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.text.google.fonts)


//    Datastore
    implementation(libs.datastore)

//    Navigation
    implementation(libs.navigation)
    implementation(libs.androidx.ui.controller)

//    Splash
    implementation(libs.splash)

//    Material3
    implementation(libs.material3)

//    Hilt
    implementation(libs.hilt)
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler)

//    Room
    implementation(libs.room)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    testImplementation(libs.room.test)

//    Chuck
    debugImplementation(libs.chuck.debug)
    testImplementation(libs.chuck.release)

//     Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.adapter)

//    okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

//    Gson
    implementation(libs.gson)

//    Coroutines
    implementation(libs.coroutines)

//    Coil
    implementation(libs.coil)

//    Lottie
    implementation(libs.lottie)




    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//    Coroutines Test
    testImplementation(libs.coroutines.test)
    testImplementation(libs.core.test)
    testImplementation(libs.mock)
    testImplementation(libs.mock.webserver)
}

kapt {
    correctErrorTypes = true
}

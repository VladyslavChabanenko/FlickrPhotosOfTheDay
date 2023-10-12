plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.chb.flickrphotosoftheday"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chb.flickrphotosoftheday"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    // core
    implementation("androidx.core:core-ktx:${Versions.coreKtxVersion}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompatVersion}")
    implementation("com.google.android.material:material:${Versions.materialVersion}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navFragmentKtxVersion}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navUiKtxVersion}")
    implementation("androidx.activity:activity-ktx:${Versions.activityKtxVersion}")

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodelKtxVersion}")
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime:${Versions.lifeCycleRuntimeVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntimeKtxVersion}")

    // hilt
    implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hiltVersion}")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCoreVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroidVersion}")

    // glide
    implementation("com.github.bumptech.glide:glide:${Versions.glideVersion}")

    // lottie
    implementation("com.airbnb.android:lottie:${Versions.lottieVersion}")

    // log
    implementation("com.jakewharton.timber:timber:${Versions.timberVersion}")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.gsonConverterVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptorVersion}")

    // room
    implementation("androidx.room:room-runtime:${Versions.roomVersion}")
    kapt("androidx.room:room-compiler:${Versions.roomVersion}")
    implementation("androidx.room:room-ktx:${Versions.roomVersion}")
}

object Versions {

    // core
    const val coreKtxVersion = "1.12.0"
    const val appCompatVersion = "1.6.1"
    const val materialVersion = "1.9.0"
    const val constraintLayoutVersion = "2.1.4"
    const val activityKtxVersion = "1.8.0"

    // navigation
    const val navFragmentKtxVersion = "2.7.4"
    const val navUiKtxVersion = "2.7.4"

    // lifecycle
    const val viewmodelKtxVersion = "2.6.2"
    const val lifeCycleExtVersion = "2.2.0"
    const val liveDataKtxVersion = "2.2.0"
    const val lifeCycleRuntimeVersion = "2.6.2"
    const val lifeCycleRuntimeKtxVersion = "2.6.2"

    // coroutines
    const val coroutineCoreVersion = "1.7.3"
    const val coroutineAndroidVersion = "1.7.3"

    // glide
    const val glideVersion = "4.15.1"

    // lottie
    const val lottieVersion = "6.0.0"

    // hilt
    const val hiltVersion = "2.48.1"

    // log
    const val timberVersion = "4.7.1"

    // retrofit
    const val retrofitVersion = "2.9.0"
    const val gsonConverterVersion = "2.9.0"
    const val loggingInterceptorVersion = "4.11.0"

    // room
    const val roomVersion = "2.5.2"
}

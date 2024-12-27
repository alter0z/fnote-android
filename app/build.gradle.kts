plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.fiberlance.fnote"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fiberlance.fnote"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"http://192.168.2.182:2000\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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
    // Hilt for Dependency Injection
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.work)
    kapt(libs.hilt.android.compiler)

    // Retrofit for networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Coroutine support
    implementation(libs.kotlinx.coroutines.android)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.androidx.work.runtime)

    // http logger
    implementation(libs.logging.interceptor)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
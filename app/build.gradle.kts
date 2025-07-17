plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.apps.custombutton"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.apps.custombutton"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
////        kotlinCompilerVersion = ""
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

//    implementation(platform("com.github.brunonavarro:CustomButtonLib:3.2025-07-16"))
//
//    // --- Now use CustomButtonLib without specifying its version ---
//    // The BOM will provide the version "1.0.0-beta6" for it.
//    implementation("com.github.brunonavarro:CustomButtonLib")
//
//    // --- Use other dependencies managed by your BOM ---
//    // For example, com.google.android.material:material
//    // is in your BOM's dependencyManagement
//    implementation("com.google.android.material:material")

}
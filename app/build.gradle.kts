plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.hidayat.photoeditor"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hidayat.photoeditor"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"

        renderscriptTargetApi = 21
        renderscriptSupportModeEnabled = true

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-cast-framework:21.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.github.dhaval2404:imagepicker:2.1")
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation(files("libs/ds-photo-editor-sdk-v10.aar"))
    implementation("io.reactivex.rxjava2:rxjava:2.1.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.0.1")
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.1")
    implementation("com.karumi:dexter:6.2.3")
}
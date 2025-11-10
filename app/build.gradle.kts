plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Firebase (plugins)
    alias(libs.plugins.google.services)      // com.google.gms.google-services
    alias(libs.plugins.firebase.crashlytics) // com.google.firebase.crashlytics (opcional)
}

// ⚠️ Repositorios redundantes a nivel de módulo para evitar "Could not find ...firebase-xxx-ktx"
repositories {
    google()
    mavenCentral()
    maven(url = "https://maven.google.com")
}

android {
    namespace = "com.example.papeleriajr"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.papeleriajr"
        minSdk = 26
        targetSdk = 36
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
            firebaseCrashlytics { mappingFileUploadEnabled = true }
        }
        debug {
            firebaseCrashlytics { mappingFileUploadEnabled = false }
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE*"
            excludes += "/META-INF/NOTICE*"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    // ---------------- Compose BOM ----------------
    implementation(platform(libs.androidx.compose.bom))

    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Base
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)

    // AÑADIDO: Librería de Material Components para temas XML (soluciona el error del tema no encontrado)
    implementation("com.google.android.material:material:1.13.0")

    // Compose UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)

    // Navegación + Iconos + Imágenes
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.coil.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // ----------------- Firebase (BoM + módulos directos) -----------------
    implementation(platform("com.google.firebase:firebase-bom:34.5.0"))

    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-crashlytics")
    // ---------------------------------------------------------------------

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}

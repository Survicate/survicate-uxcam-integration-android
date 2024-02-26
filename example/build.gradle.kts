import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.survicate.uxcam.integration.example"

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.survicate.uxcam.integration.example"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localPropertiesFile = rootProject.file("local.properties")
        val properties = Properties()
        if (localPropertiesFile.exists() && localPropertiesFile.canRead()) {
            properties.load(localPropertiesFile.reader())
        }
        val uxcamKey = (properties["uxcam.key"] as? String).orEmpty()
        resValue("string", "uxcam_key", uxcamKey)
        val survicateKey = (properties["survicate.key"] as? String).orEmpty()
        resValue("string", "survicate_key", survicateKey)
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    implementation(project(mapOf("path" to ":lib")))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.survicate.sdk)
    implementation(libs.uxcam)
}

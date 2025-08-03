plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.ksp)
}

android {
    compileSdk = 35

    defaultConfig {
        applicationId = "com.github.ThunderTube"
        minSdk = 26
        targetSdk = 35
        versionCode = 62
        versionName = "0.28.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resValue("string", "app_name", "ThunderTube")

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("exportSchema", "true")
        }
    }

    viewBinding {
        enable = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            resValue("string", "app_name", "ThunderTube")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            isUniversalApk = true
        }
    }

    packaging {
        jniLibs.excludes.add("lib/armeabi-v7a/*_neon.so")
    }

    tasks.register("testClasses")

    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }

    buildFeatures {
        buildConfig = true
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }

    androidResources {
        generateLocaleConfig = true
    }

    namespace = "com.github.ThunderTube"
}

dependencies {
    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.preference)
    implementation(libs.androidx.documentfile)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.collection)
    implementation(libs.androidx.media)
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.service)

    implementation(libs.material)

    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.exoplayer.hls)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.session)

    implementation(libs.square.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.datetime)
    implementation(libs.converter.kotlinx.serialization)

    implementation(libs.newpipeextractor)

    coreLibraryDesugaring(libs.desugaring)
    implementation(libs.coil)
    implementation(libs.coil.network.okhttp)

    ksp(libs.room.compiler)
    implementation(libs.room)

    implementation(libs.androidx.profileinstaller)
    baselineProfile(project(":baselineprofile"))

    implementation(libs.androidx.paging)

    testImplementation(libs.junit)
}

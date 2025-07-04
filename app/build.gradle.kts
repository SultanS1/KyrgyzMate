plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    alias(libs.plugins.google.gms.google.services)
}

fun com.android.build.api.dsl.DefaultConfig.addBuildConfigFields(fields: Any?) {
    fields ?: return
    val buildConfigFields = fields as Map<String, String>
    buildConfigFields.forEach { (key, value) ->
        buildConfigField("String", key, "\"$value\"")
    }
}

android {
    namespace = "alatoo.edu.kg.kyrgyzmate"
    compileSdk = 35

    defaultConfig {
        applicationId = "alatoo.edu.kg.kyrgyzmate"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_KEY", "\"${project.property("API_KEY")}\"")
        buildConfigField("String", "USERS_TABLE_REFERENCE","\"${project.property("USERS_TABLE_REFERENCE")}\"")
        buildConfigField("String", "GROUPS_TABLE_REFERENCE","\"${project.property("GROUPS_TABLE_REFERENCE")}\"")
        buildConfigField("String", "VIDEO_MATERIALS","\"${project.property("VIDEO_MATERIALS")}\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //jetpack libs
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    androidTestImplementation(libs.androidx.espresso.core.v351)


    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //viewBindingPropertyDelegate
    implementation(libs.viewbindingpropertydelegate.noreflection)

    //koin
    implementation (libs.koin.core)
    implementation (libs.koin.android)

    //Firebase
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database.ktx)

    //shimmer
    implementation(libs.shimmer)

    //translator
    implementation(libs.translate)

    // CameraX
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
}
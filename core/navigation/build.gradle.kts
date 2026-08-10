plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.navigation"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }

}

dependencies {
    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)

    // navigation3
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)

    // default start key
    implementation(project(":feature:featurelist:api"))
    implementation(project(":feature:featurelist:impl"))

    // myfeature
    implementation(project(":feature:myfeature:impl"))

    // featre flows
    implementation(project(":feature:flows:impl"))
    // featre coroutines
    implementation(project(":feature:coroutines:impl"))
    // feature user-settings
    implementation(project(":feature:user-settings:impl"))



//    // navigation 3
//    implementation(libs.androidx.navigation3.ui)
//    implementation(libs.androidx.navigation3.runtime)
//    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
//    implementation(libs.androidx.material3.adaptive.navigation3)
//    implementation(libs.kotlinx.serialization.core)

}
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.featurelist.ui"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures{
        compose = true
    }

}

dependencies {

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation("androidx.compose.material:material-icons-extended")

    //  = viewModel()
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.11.0")
    // async image
    implementation("io.coil-kt:coil-compose:2.7.0")

    // navKey
    implementation(libs.androidx.navigation3.runtime)

    // particular navigation pattern of this app
    implementation(project(":feature:myfeature:api"))
    implementation(project(":feature:coroutines:api"))
    implementation(project(":feature:flows:api"))

    implementation(project(":core:designsystem"))
}
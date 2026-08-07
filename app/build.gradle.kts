plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.coroutinesflows"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.coroutinesflows"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // navigation 3
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.adaptive.navigation3)
    implementation(libs.kotlinx.serialization.core)



    // hilt
    implementation("com.google.dagger:hilt-android:2.60.1")
    ksp("com.google.dagger:hilt-android-compiler:2.60.1")

    // hilt viewmodel
    implementation("androidx.hilt:hilt-lifecycle-viewmodel-compose:1.4.0")

    // okhttp
    implementation("com.squareup.okhttp3:okhttp:5.4.0")
    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:3.0.0")
    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")

    // :data module is needed here, and that is exactly beacuse:
    // error: [Dagger/MissingBinding] com.example.domain.repository.MyRepository
    // cannot be provided without an @Provides-annotated method.
    // so iv added @Provides that trew another error: @Binds and @Provides cannot be both
    // then iv searched in google, there is written: features modules are different, by luck ther arent any here.
    // ... somehow the :app module needs to know about the MyRepository or/and MyRepositoryImpl
    // one is in :domain, the other is in :data(<--:domain)
    // https://github.com/romychab/example-hilt-in-pure-kotlin has all 3 in the app module
    // to me it works also with just data
    // implementation(project(":domain"))
    implementation(project(":data"))
    // because im not using ai yet, while googling brings not that much,
    // i would be glad not to be tagged as a production bottleneck if in such cases
    // would i quickly schedule a call with some senior, even if the senior is jounger than me.


    // theme
    implementation(project(":core:designsystem"))

    // navigation
    implementation(project(":core:navigation"))

    // Screens
    implementation(project(":presentation"))


}
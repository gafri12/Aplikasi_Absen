plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.aplikasiabsen"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aplikasiabsen"
        minSdk = 29
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
}

dependencies {
    implementation("com.google.android.gms:play-services-location:7.+")
    implementation("com.google.android.gms:play-services-location:15.0.1")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    //implementation("mysql:mysql-connector-java:5.1.49")
    implementation(files("libs\\mysql-connector-j-9.1.0.jar"))
    //implementation fileTree("libs/mysql-connector-j-9.1.0.jar")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}
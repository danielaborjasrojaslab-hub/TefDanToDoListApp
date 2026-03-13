plugins {

    // Plugin que permite crear una aplicación Android
    alias(libs.plugins.android.application)
}

android {

    // Nombre del paquete de la aplicación
    namespace = "com.example.tefdantodolistapp"

    // Versión del SDK con la que se compila la app
    compileSdk = 35


    defaultConfig {

        // Identificador único de la aplicación
        applicationId = "com.example.tefdantodolistapp"

        // Versión mínima de Android que puede instalar la app
        minSdk = 24

        // Versión objetivo de Android
        targetSdk = 35

        // Número de versión interno
        versionCode = 1

        // Versión visible para el usuario
        versionName = "1.0"

        // Runner para pruebas automatizadas
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }



    buildTypes {

        release {

            // Indica si el código se optimiza o se reduce
            isMinifyEnabled = false

            // Archivos de reglas de ProGuard
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }



    compileOptions {

        // Versión de Java utilizada para compilar
        sourceCompatibility = JavaVersion.VERSION_11

        // Compatibilidad con Java 11
        targetCompatibility = JavaVersion.VERSION_11
    }
}



dependencies {

    // Librería base de Android para compatibilidad
    implementation(libs.appcompat)

    // Componentes visuales de Material Design
    implementation(libs.material)

    // Manejo de Activities (esta es la que causó el conflicto)
    implementation(libs.activity)

    // Sistema de diseño con ConstraintLayout
    implementation(libs.constraintlayout)

    // Librería para pruebas unitarias
    testImplementation(libs.junit)

    // Pruebas Android
    androidTestImplementation(libs.ext.junit)

    // Framework para pruebas de interfaz
    androidTestImplementation(libs.espresso.core)
}
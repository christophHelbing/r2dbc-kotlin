plugins {
    kotlin(KotlinJvm) version Versions.kotlinVersion
}

group = "de.chelbing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependencies.r2dbc)
    implementation(Dependencies.kotlinxCoroutines)
    implementation(Dependencies.kotlinxReactor)
    testImplementation(kotlin("test"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf ("-Xcontext-receivers")
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
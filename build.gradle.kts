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

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
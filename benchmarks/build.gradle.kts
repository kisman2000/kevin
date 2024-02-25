plugins {
    id("java")
    id("me.champeau.jmh").version("0.7.2")
    kotlin("jvm") version "1.9.0"
    application
}

group = "ksmn"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.meteordev.org/releases")
    maven("https://jitpack.io")
}

dependencies {
    implementation(project(mapOf("path" to ":")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("meteordevelopment:orbit:0.2.3")
    implementation("org.openjdk.jmh:jmh-core:1.37")
    implementation("org.openjdk.jmh:jmh-generator-annprocess:1.37")
    jmhAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")
//    implementation(files("../build/libs/kevin-1.0.jar"))
    implementation(files("libs/norbit-1.1.0.jar"))
    implementation("com.github.ZeroMemes:Alpine:3.1.0")
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}
plugins {
    id("java")
    id("maven-publish")
    application
}

repositories {
    mavenCentral()
    maven("https://maven.meteordev.org/releases")
    maven("https://jitpack.io")
}

group = "com.github.kisman2000"
version = "2.0"

dependencies {
    testImplementation("org.openjdk.jmh:jmh-core:1.37")
    testImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.37")

    testAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")

    testImplementation("meteordevelopment:orbit:0.2.4")
    testImplementation("io.github.racoondog:norbit:1.1.0")
    testImplementation("com.github.ZeroMemes:Alpine:3.1.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "lavahack"
            artifactId = "kevin"

            artifact(tasks.jar) {
                classifier = "core"
            }
        }
    }
}
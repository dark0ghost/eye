import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
}

group = "org.dark0ghost"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit"))

    implementation("io.ktor:ktor-network:1.6.1")

    implementation ("org.bytedeco:javacv:1.5.5")
    implementation ("org.bytedeco.javacpp-presets:opencv:3.4.1-1.4.1")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "15"
}

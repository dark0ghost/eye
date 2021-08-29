import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
}

group = "org.dark0ghost"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.30")

    implementation("io.ktor:ktor-network:1.6.2")

    implementation ("org.bytedeco:javacv:1.5.5")
    implementation ("org.bytedeco.javacpp-presets:opencv:4.0.1-1.4.4")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "15"
}

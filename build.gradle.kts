plugins {
    kotlin("jvm") version "1.5.31"
}

group = "org.dark0ghost"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.30")

    implementation("io.ktor:ktor-network:1.6.3")

    implementation ("org.bytedeco:javacv:1.5.5")
    implementation ("org.bytedeco.javacpp-presets:opencv:4.0.1-1.4.4")
}

tasks.test {
    useJUnit()
}



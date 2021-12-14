plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://server.bbkr.space/artifactory/libs-release/")
    maven("https://maven.quiltmc.org/repository/release/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation("net.fabricmc:fabric-loom:0.10-SNAPSHOT")
    implementation("io.github.juuxel:loom-quiltflower-mini:1.2.1")
    implementation("org.quiltmc:quilt-mappings-on-loom:3.1.2")
    implementation("io.papermc.paperweight.userdev:io.papermc.paperweight.userdev.gradle.plugin:1.3.2")
}

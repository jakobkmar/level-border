plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://server.bbkr.space/artifactory/libs-release/")
    maven("https://maven.quiltmc.org/repository/release/")
}

dependencies {
    implementation("net.fabricmc:fabric-loom:0.10-SNAPSHOT")
    implementation("io.github.juuxel:loom-quiltflower-mini:1.2.1")
    implementation("org.quiltmc:quilt-mappings-on-loom:3.1.2")
}

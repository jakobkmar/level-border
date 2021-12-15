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
    maven("https://maven.minecraftforge.net")
}

dependencies {
    implementation("net.fabricmc:fabric-loom:0.10-SNAPSHOT")
    implementation("io.github.juuxel:loom-quiltflower-mini:1.2.1")
    implementation("org.quiltmc:quilt-mappings-on-loom:3.1.2")

    implementation("io.papermc.paperweight.userdev:io.papermc.paperweight.userdev.gradle.plugin:1.3.2")
    implementation("xyz.jpenilla:run-paper:1.0.6")
    implementation("net.minecrell.plugin-yml.bukkit:net.minecrell.plugin-yml.bukkit.gradle.plugin:0.5.1")

    implementation("net.minecraftforge.gradle:ForgeGradle:5.1.+")

    implementation("org.spongepowered.gradle.plugin:org.spongepowered.gradle.plugin.gradle.plugin:2.0.0")
}

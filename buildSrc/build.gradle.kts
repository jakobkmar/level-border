plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://server.bbkr.space/artifactory/libs-release/")
    maven("https://maven.quiltmc.org/repository/release/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.minecraftforge.net")
}

dependencies {
    fun pluginDep(id: String, version: String) = "${id}:${id}.gradle.plugin:${version}"

    implementation("net.fabricmc:fabric-loom:1.0-SNAPSHOT")
    implementation("io.github.juuxel:loom-quiltflower:1.7.4")

    implementation("io.papermc.paperweight.userdev:io.papermc.paperweight.userdev.gradle.plugin:1.3.9")
    implementation(pluginDep("xyz.jpenilla.run-paper", "2.0.0"))
    implementation("net.minecrell.plugin-yml.bukkit:net.minecrell.plugin-yml.bukkit.gradle.plugin:0.5.2")

    implementation("net.minecraftforge.gradle:ForgeGradle:5.1.+")

    implementation("org.spongepowered.gradle.plugin:org.spongepowered.gradle.plugin.gradle.plugin:2.1.1")

    implementation("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
    implementation(pluginDep("com.modrinth.minotaur", "2.+"))
}

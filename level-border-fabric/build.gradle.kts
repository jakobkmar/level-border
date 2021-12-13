plugins {
    id("fabric-loom") version "0.10-SNAPSHOT"
    id("org.quiltmc.quilt-mappings-on-loom") version "3.1.1"
    id("io.github.juuxel.loom-quiltflower-mini") version "1.2.1"
}

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.18.1")
    mappings(loom.layered {
        addLayer(quiltMappings.mappings("org.quiltmc:quilt-mappings:1.18.1+build.1:v2"))
        officialMojangMappings()
    })
    modImplementation("net.fabricmc:fabric-loader:0.12.11")
}

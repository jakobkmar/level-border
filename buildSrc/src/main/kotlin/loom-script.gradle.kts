plugins {
    id("fabric-loom")
    id("org.quiltmc.quilt-mappings-on-loom")
    id("io.github.juuxel.loom-quiltflower-mini")
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
}

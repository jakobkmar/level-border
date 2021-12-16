@file:Suppress("HasPlatformType")

plugins {
    java
    id("net.minecraftforge.gradle")
}

minecraft {
    mappings("official", minecraftVersion)
}

dependencies {
    // version from https://files.minecraftforge.net/net/minecraftforge/forge/
    minecraft("net.minecraftforge:forge:${minecraftVersion}-39.0.5")
}

val embed by configurations.creating

tasks {
    jar {
        from(embed.map { if (it.isDirectory) it else zipTree(it) })
        finalizedBy("reobfJar")
    }
}

plugins {
    java
    id("net.minecraftforge.gradle")
}

minecraft {
    mappings("official", minecraftVersion)
}

dependencies {
    // version from https://files.minecraftforge.net/net/minecraftforge/forge/
    minecraft ("net.minecraftforge:forge:${minecraftVersion}-39.0.5")
}

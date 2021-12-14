plugins {
    java
    id("net.minecraftforge.gradle")
}

minecraft {
    mappings("official", "1.18.1")
}

dependencies {
    // version from https://files.minecraftforge.net/net/minecraftforge/forge/
    minecraft ("net.minecraftforge:forge:1.18.1-39.0.5")
}

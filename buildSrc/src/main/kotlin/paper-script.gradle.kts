plugins {
    java
    id("io.papermc.paperweight.userdev")
    id("xyz.jpenilla.run-paper")
}

dependencies {
    paperDevBundle("1.18.1-R0.1-SNAPSHOT")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
}

plugins {
    `java-script`
    `forge-script`
    `mod-properties-script`
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

dependencies {
    implementation(project(":${rootProject.name}-vanilla", configuration = "namedElements"))
}

val modConfigFile by extra("META-INF/mods.toml")

tasks {
    shadowJar {
        dependencies {
            exclude {
                it.moduleGroup != "net.axay"
            }
        }
    }
}

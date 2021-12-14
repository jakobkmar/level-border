plugins {
    `java-script`
    `paper-script`
    `mod-properties-script`
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

dependencies {
    implementation(project(":${rootProject.name}-common", configuration = "namedElements"))
}

val modConfigFile by extra("plugin.yml")

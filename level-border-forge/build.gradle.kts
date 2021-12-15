plugins {
    `java-script`
    `forge-script`
    `mod-properties-script`
    `shadow-script`
}

dependencies {
    implementation(project(":${rootProject.name}-vanilla", configuration = "namedElements"))
}

val modConfigFile by extra("META-INF/mods.toml")

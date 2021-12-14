plugins {
    `java-script`
    `forge-script`
    `mod-properties-script`
}

dependencies {
    implementation(project(":${rootProject.name}-common", configuration = "namedElements"))
}

val modConfigFile by extra("META-INF/mods.toml")

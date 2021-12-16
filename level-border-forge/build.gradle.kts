plugins {
    `java-script`
    `forge-script`
    `mod-properties-script`
}

@Suppress("HasPlatformType")
val embed by configurations.getting

dependencies {
    embed(implementation(project(":${rootProject.name}-vanilla", configuration = "namedElements"))!!)
}

val modConfigFile by extra("META-INF/mods.toml")

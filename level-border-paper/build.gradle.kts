plugins {
    `java-script`
    `paper-script`
    `mod-properties-script`
    `shadow-script`
}

dependencies {
    implementation(project(":${rootProject.name}-vanilla", configuration = "namedElements"))
}

val modConfigFile by extra("plugin.yml")

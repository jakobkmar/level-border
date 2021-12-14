plugins {
    `java-script`
    `loom-script`
    `mod-properties-script`
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:0.12.11")
    include(implementation(project(":${rootProject.name}-common", configuration = "namedElements"))!!)
}

val modConfigFile by extra("fabric.mod.json")

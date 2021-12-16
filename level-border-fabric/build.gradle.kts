plugins {
    `java-script`
    `loom-script`
    `mod-properties-script`
    `modrinth-script`
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:0.12.11")
    include(implementation(project(":${rootProject.name}-common"))!!)
    include(implementation(project(":${rootProject.name}-vanilla", configuration = "namedElements"))!!)
}

ext {
    set("modConfigFile", "fabric.mod.json")
    set("loaderNames", "Fabric")
    set("loaderSlug", "fabric")
}

tasks {
    named<com.modrinth.minotaur.TaskModrinthUpload>("uploadModrinth") {
        uploadFile = remapJar
        addLoader("fabric")
    }
}

plugins {
    `java-script`
    `forge-script`
    `mod-properties-script`
    `modrinth-script`
}

@Suppress("HasPlatformType")
val embed by configurations.getting

dependencies {
    embed(implementation(project(":${rootProject.name}-vanilla", configuration = "namedElements"))!!)
}

ext {
    set("modConfigFile", "META-INF/mods.toml")
    set("loaderNames", "Forge")
    set("loaderSlug", "forge")
}

tasks {
    named<com.modrinth.minotaur.TaskModrinthUpload>("uploadModrinth") {
        dependsOn(jar)

        uploadFile = project.tasks.getByName("reobfJar")
        addLoader("forge")
    }
}

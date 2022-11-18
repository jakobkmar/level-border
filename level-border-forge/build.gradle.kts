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

modrinth {
    uploadFile.set(tasks.jar.get())
}

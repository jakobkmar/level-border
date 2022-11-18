plugins {
    id("com.modrinth.minotaur")
}

modrinth {
    token.set(findProperty("modrinth.token").toString())

    projectId.set("zyAuOrGS")

    versionName.set("Level Border [${findProperty("loaderNames")}] ${project.version}")
    versionNumber.set("${project.version}-${modVersionType.name.toLowerCase()}-${findProperty("loaderSlug")}")

    gameVersions.add(minecraftVersion)
    versionType.set(modVersionType.name.toLowerCase())
    detectLoaders.set(false)

    loaders.add(findProperty("loaderSlug").toString())
}

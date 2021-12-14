plugins {
    java
}

val modConfigFile: String by extra

tasks {
    processResources {
        val modProps = linkedMapOf(
            "modId" to rootProject.name,
            "modName" to projectDisplayName,
            "modVersion" to project.version.toString(),
            "modAuthor" to author,
            "modDescription" to project.description.toString(),
            "modLicense" to licenseName,
            "websiteUrl" to githubUrl,
            "issuesUrl" to issuesUrl,
            "minecraftVersion" to majorMinecraftVersion
        )

        inputs.properties(modProps)

        filesMatching(modConfigFile) {
            expand(modProps)
        }
    }
}

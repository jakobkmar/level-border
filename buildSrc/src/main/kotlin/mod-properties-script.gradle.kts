plugins {
    java
}

val modConfigFile: String by extra

tasks {
    processResources {
        val modProps = linkedMapOf(
            "modId" to rootProject.name,
            "modName" to "Level = Border",
            "modVersion" to project.version.toString(),
            "modAuthor" to author,
            "modDescription" to project.description.toString(),
            "websiteUrl" to githubUrl,
            "issuesUrl" to "$githubUrl/issues",
            "minecraftVersion" to minecraftVersion
        )

        inputs.properties(modProps)

        filesMatching(modConfigFile) {
            expand(modProps)
        }
    }
}

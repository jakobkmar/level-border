import com.modrinth.minotaur.TaskModrinthUpload

plugins {
    id("com.modrinth.minotaur")
}

tasks {
    register<TaskModrinthUpload>("uploadModrinth") {
        group = "upload"
        onlyIf {
            findProperty("modrinth.token") != null
        }

        token = findProperty("modrinth.token").toString()

        projectId = "zyAuOrGS"

        versionName = "Level Border [${ext.get("loaderNames")}] ${project.version}-${modVersionType.name.toLowerCase()}-${ext.get("loaderSlug")}"
        versionNumber = project.version.toString()

        addGameVersion(minecraftVersion)
        versionType = modVersionType
        detectLoaders = false
    }
}

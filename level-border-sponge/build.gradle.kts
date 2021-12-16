plugins {
    `java-script`
    `sponge-script`
    `shadow-script`
    `modrinth-script`
}

dependencies {
    implementation(project(":${rootProject.name}-common"))
}

ext {
    set("loaderNames", "Sponge")
    set("loaderSlug", "sponge")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }

    named<com.modrinth.minotaur.TaskModrinthUpload>("uploadModrinth") {
        dependsOn(shadowJar)

        uploadFile = shadowJar.get()
        addLoader("forge")

        // sponge is not on the latest version
        gameVersions.clear()
        addGameVersion("1.17.1")

        // sponge does not have all features
        versionType = com.modrinth.minotaur.request.VersionType.BETA
    }
}

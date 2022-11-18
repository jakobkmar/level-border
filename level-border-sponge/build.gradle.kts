plugins {
    `java-script`
    `sponge-script`
    `shadow-script`
    `modrinth-script`
}

dependencies {
    implementation(project(":${rootProject.name}-common"))
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }
}

modrinth {
    uploadFile.set(tasks.shadowJar.get())

    gameVersions.set(listOf("1.18.1"))

    // sponge does not have all features
    versionType.set("beta")
}

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
    named<com.modrinth.minotaur.TaskModrinthUpload>("uploadModrinth") {
        uploadFile = shadowJar
        addLoader("forge")
    }
}

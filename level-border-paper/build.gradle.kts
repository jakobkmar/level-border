plugins {
    `java-script`
    `paper-script`
    `mod-properties-script`
    `shadow-script`
    `modrinth-script`
}

dependencies {
    implementation(project(":${rootProject.name}-vanilla", configuration = "namedElements"))
}

ext {
    set("modConfigFile", "plugin.yml")
    set("loaderNames", "Paper")
    set("loaderSlug", "paper")
}

tasks {
    named<com.modrinth.minotaur.TaskModrinthUpload>("uploadModrinth") {
        dependsOn(reobfJar)

        uploadFile = reobfJar.get().outputJar
        addLoader("fabric")
    }
}

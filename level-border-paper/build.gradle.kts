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
    set("loaderNames", "Paper & Spigot")
    set("loaderSlug", "paper")
}

tasks {
    named<com.modrinth.minotaur.TaskModrinthUpload>("uploadModrinth") {
        dependsOn(shadowJar)

        uploadFile = shadowJar.get()
        addLoader("fabric")
    }
}

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

modrinth {
    uploadFile.set(tasks.jar.get())
}

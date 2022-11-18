plugins {
    `java-script`
    `loom-script`
    `mod-properties-script`
    `modrinth-script`
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:0.14.10")
    include(implementation(project(":${rootProject.name}-common"))!!)
    include(implementation(project(":${rootProject.name}-vanilla", configuration = "namedElements"))!!)
}

modrinth {
    uploadFile.set(tasks.remapJar.get())
}

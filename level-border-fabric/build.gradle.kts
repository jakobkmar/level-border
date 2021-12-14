plugins {
    `java-script`
    `loom-script`
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:0.12.11")
    include(implementation(project(":${rootProject.name}-common", configuration = "namedElements"))!!)
}

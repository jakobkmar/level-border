plugins {
    `java-script`
    `loom-script`
}

dependencies {
    modCompileOnly("net.fabricmc:fabric-loader:0.12.11")
    api(project(":${rootProject.name}-common"))
}

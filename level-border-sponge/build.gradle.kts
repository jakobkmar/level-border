plugins {
    `java-script`
    `sponge-script`
}

dependencies {
    implementation(project(":${rootProject.name}-common", configuration = "namedElements"))
}

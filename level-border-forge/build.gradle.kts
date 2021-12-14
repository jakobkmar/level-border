plugins {
    `java-script`
    `forge-script`
}

dependencies {
    implementation(project(":${rootProject.name}-common", configuration = "namedElements"))
}

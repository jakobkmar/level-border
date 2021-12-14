plugins {
    `java-script`
    `paper-script`
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

dependencies {
    implementation(project(":${rootProject.name}-common"))
}

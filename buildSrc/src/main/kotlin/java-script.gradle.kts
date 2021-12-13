val javaVersion = 17

plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

repositories {
    mavenCentral()
}

tasks {
    withType<JavaCompile> {
        options.release.set(javaVersion)
        options.encoding = "UTF-8"
    }
}

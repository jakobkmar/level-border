val javaVersion = 17

plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

allprojects {
    apply {
        plugin("java")
    }

    group = "net.axay"
    version = "1.0.0"

    description = ""

    repositories {
        mavenCentral()
    }

    tasks {
        withType<JavaCompile> {
            options.release.set(javaVersion)
            options.encoding = "UTF-8"
        }
    }
}

plugins {
    java
    id("org.spongepowered.gradle.vanilla")
    id("org.spongepowered.plugin")
}

repositories {
    maven("https://repo.spongepowered.org/maven")
}

dependencies {
    compileOnly("org.spongepowered:spongeapi:7.2.0")
}

minecraft {
    version("1.17.1")

    platform(org.spongepowered.gradle.vanilla.repository.MinecraftPlatform.JOINED)
}

sponge {
    plugin {
        meta {
            url = githubUrl
            authors = listOf(author)
        }
    }
}

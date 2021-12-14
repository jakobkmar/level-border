import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.gradle.vanilla.repository.MinecraftPlatform

plugins {
    java
    id("org.spongepowered.gradle.vanilla")
    id("org.spongepowered.gradle.plugin")
}

minecraft {
    version("1.17.1")

    platform(MinecraftPlatform.JOINED)
}

sponge {
    apiVersion("7.2.0")
    license(licenseName)
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }
    plugin(rootProject.name) {
        displayName(projectDisplayName)
        entrypoint("net.axay.levelborder.sponge.LevelBorderPlugin")
        links {
            homepage(githubUrl)
            source(githubUrl)
            issues(issuesUrl)
        }
        contributor(author) {
            description("Developer")
        }
        dependency("spongeapi") {
            optional(false)
        }
    }
}

import org.spongepowered.gradle.plugin.config.PluginLoaders

plugins {
    java
    id("org.spongepowered.gradle.plugin")
}

sponge {
    apiVersion("9.0.0")
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

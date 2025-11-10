pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        // Refuerzo explícito (algunas instalaciones lo requieren)
        maven { url = uri("https://maven.google.com") }
    }
}

dependencyResolutionManagement {
    // Permite que los repos del settings dominen, pero sin bloquear repos en módulos si los declaramos
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        // Refuerzo adicional
        maven { url = uri("https://maven.google.com") }
    }
}

rootProject.name = "PapeleriaJR"
include(":app")

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
        maven("https://jitpack.io") // ¡Añade esta línea!
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") // ¡Añade esta línea!
    }
//    // ¡Habilita los catálogos de versiones aquí!
//    versionCatalogs {
//        create("libs") { // "libs" será el nombre de tu catálogo (puedes elegir otro)
//            from(files("gradle/libs.versions.toml")) // Ruta a tu archivo TOML
//        }
//    }
}

rootProject.name = "CustomButton"
include(":app")
include(":CustomButtonLib")
include(":BOM")

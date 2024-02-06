pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://repo.survicate.com")
        maven("https://sdk.uxcam.com/android/")
        mavenLocal()
    }
}

rootProject.name = "survicate-uxcam-integration"
include(":lib")
include(":example")

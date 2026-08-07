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
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CoroutinesFlows"
include(":app")
include(":domain")
include(":data")
include(":presentation")
include(":core:designsystem")
include(":core:navigation")
// a random feature to choose navigation architectures within modules
include(":feature:myfeature:api")
include(":feature:myfeature:impl")

include(":feature:featurelist:api")
include(":feature:featurelist:impl")
include(":feature:featurelist:ui")

include(":feature:flows:api")
include(":feature:flows:impl")
include(":feature:coroutines:api")
include(":feature:coroutines:impl")

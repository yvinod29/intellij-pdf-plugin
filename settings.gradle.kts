rootProject.name = "intellij-pdf-viewer"

include(":web-view")
include(":web-view:viewer")
include(":web-view:bootstrap")
include(":plugin")
include(":mpi")
include(":model")


pluginManagement {
  plugins {
    val kotlinVersion: String by settings
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    kotlin("multiplatform") version kotlinVersion
  }
}
plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

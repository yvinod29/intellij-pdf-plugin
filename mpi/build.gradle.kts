plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
}

val kotlinVersion: String by project
val kotlinxSerializationJsonVersion: String by project

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

kotlin {
  jvm()
  js(IR) {
    browser {
      binaries.executable()
    }
  }
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(kotlin("stdlib-common"))
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationJsonVersion")
      }
    }
    val jsMain by getting {
      dependencies {
        implementation(npm("moment", "2.29.4")) // Add moment.js as an NPM dependency
      }
    }
  }
  targets.all {
    compilations.all {
      compilerOptions.configure {
        freeCompilerArgs.add("-Xexpect-actual-classes")
      }
    }
  }
}

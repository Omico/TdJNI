import me.omico.age.spotless.configureSpotless
import me.omico.age.spotless.intelliJIDEARunConfiguration
import me.omico.age.spotless.kotlin
import me.omico.age.spotless.kotlinGradle

plugins {
    kotlin("jvm") version "1.8.10" apply false
    id("com.diffplug.spotless") version "6.17.0"
    id("me.omico.age.spotless") version "1.0.0-SNAPSHOT"
}

allprojects {
    configureSpotless {
        cpp {
            target("jni/**/*.cpp")
            clangFormat("15.0.7")
        }
        intelliJIDEARunConfiguration()
        java {
            target("**/*.java")
            targetExclude("tdlib/**/*.java")
            eclipse()
            indentWithSpaces()
            trimTrailingWhitespace()
            endWithNewline()
        }
        kotlin()
        kotlinGradle()
    }
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "8.0.2"
}

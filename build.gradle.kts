import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val javaVersion = JvmTarget.JVM_21
val silkVersion = "1.10.7"
val minecraftVersion = property("minecraft.version")

plugins {
    kotlin("jvm") version "2.1.0"
    id("fabric-loom") version "1.9-SNAPSHOT"
    `maven-publish`
}

group = "com.github.mccreativelab"
version = "1.0+$minecraftVersion"

repositories {
    mavenCentral()
    maven("https://maven.parchmentmc.org")
    maven("https://repo.verdox.de/snapshots")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")

    @Suppress("UnstableApiUsage")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-$minecraftVersion:2024.11.17@zip")
    })

    modImplementation("net.fabricmc:fabric-loader:0.16.9")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.0+kotlin.2.1.0")

    modImplementation("net.silkmc:silk-core:$silkVersion")
    modImplementation("net.kyori", "adventure-platform-fabric", "5.14.1")

    include(implementation("de.verdox.mccreativelab.mcc-wrapper", "api", "1.21.1-R0.1-SNAPSHOT"))
    include(implementation("de.verdox.mccreativelab.mcc-wrapper", "vanilla", "1.21.1-R0.1-SNAPSHOT"))
    include(implementation("de.verdox", "vserializer", "1.0.5-SNAPSHOT"))
}

tasks {
    compileKotlin {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjdk-release=${javaVersion.target}", "-Xskip-prerelease-check")
            jvmTarget.set(javaVersion)
        }
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion.target.toInt())
    }
    processResources {
        val properties = mapOf("version" to project.version)
        inputs.properties(properties)
        filesMatching("fabric.mod.json") { expand(properties) }
    }
}

publishing {
    publications {
        create<MavenPublication>("mod") {
            from(components["java"])
        }
    }
}
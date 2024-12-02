import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val javaVersion = JvmTarget.JVM_21
val silkVersion = "1.10.7"

plugins {
    kotlin("jvm") version "2.1.0"
    id("fabric-loom") version "1.9-SNAPSHOT"
    `maven-publish`
}

group = "com.github.mccreativelab"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.3")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.16.9")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.110.0+1.21.3")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.0+kotlin.2.1.0")
    modImplementation("net.silkmc:silk-core:$silkVersion")
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
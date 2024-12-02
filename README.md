# 🔮 mcc-platform-fabric
The [FabricMC](https://fabricmc.net) implementation for [MCCreativeLab](https://github.com/MCCreativeLab/MCCreativeLab).

> [!WARNING]
> Do not use the mod yet, it's still work in progress.

## ⬇️ Installation
1. Download the latest version of the mod from the [actions page](https://github.com/MCCreativeLab/mcc-platform-fabric/actions).
2. Put the downloaded jar file into the `mods` folder of your Minecraft server.
3. Start the server and enjoy!

## 🛠️ Development
1. Clone the repository.
2. Run `./gradlew publishToMavenLocal` to publish the mod to your local maven repository.
3. Add the following to your `build.gradle.kts`:
```kotlin
val mccVersion: String by project // Add the version in the gradle.properties file

repositories {
    mavenLocal()
}

dependencies {
    modImplementation("com.github.mccreativelab:mcc-platform-fabric:${mccVersion}")
}
```

## 📜 License
This project is licensed under the [GPL-3.0 License](LICENSE).
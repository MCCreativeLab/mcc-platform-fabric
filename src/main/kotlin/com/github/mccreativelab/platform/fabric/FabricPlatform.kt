package com.github.mccreativelab.platform.fabric

import com.github.mccreativelab.platform.fabric.converter.ComponentConverter
import com.google.common.reflect.TypeToken
import de.verdox.mccreativelab.impl.vanilla.platform.NMSPlatform
import de.verdox.mccreativelab.wrapper.entity.types.MCCPlayer
import de.verdox.mccreativelab.wrapper.world.MCCWorld
import net.kyori.adventure.text.Component
import net.silkmc.silk.core.Silk
import java.util.*

class FabricPlatform : NMSPlatform() {

    override fun init() {
        super.init()
        conversionService.registerConverterForNewImplType(Component::class.java, ComponentConverter())
        LOGGER.info("Fabric platform has been initialized.")
    }

    override fun shutdown() {
        Silk.serverOrThrow.stopServer()
    }

    override fun getWorlds(): List<MCCWorld> {
        val worlds: MutableList<MCCWorld> = LinkedList()
        Silk.serverOrThrow.allLevels.forEach { serverLevel ->
            worlds.add(getConversionService().wrap(serverLevel, TypeToken.of(MCCWorld::class.java)))
        }
        return worlds
    }

    override fun getOnlinePlayer(uuid: UUID): MCCPlayer? {
        return conversionService.wrap(Silk.serverOrThrow.playerList.getPlayer(uuid))
    }

    override fun getOnlinePlayers(): List<MCCPlayer> {
        return Silk.serverOrThrow.playerList.players.map { serverPlayer ->
            getConversionService().wrap(
                serverPlayer,
                TypeToken.of(MCCPlayer::class.java)
            )
        }
    }
}
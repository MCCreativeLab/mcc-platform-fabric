package com.github.mccreativelab.platform.fabric

import de.verdox.mccreativelab.impl.vanilla.platform.NMSPlatform
import de.verdox.mccreativelab.wrapper.entity.MCCPlayer
import net.silkmc.silk.core.Silk
import java.util.*

class FabricPlatform: NMSPlatform() {

    // TODO: remove when implemented in the super class
    override fun getOnlinePlayer(uuid: UUID?): MCCPlayer? {
        return if (uuid == null) null
        else conversionService.wrap(
            Silk.server?.playerList?.getPlayer(uuid),
            MCCPlayer::class.java
        )
    }
}
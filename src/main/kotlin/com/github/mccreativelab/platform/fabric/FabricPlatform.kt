package com.github.mccreativelab.platform.fabric

import com.github.mccreativelab.platform.fabric.converter.ComponentConverter
import de.verdox.mccreativelab.impl.vanilla.platform.NMSPlatform
import net.kyori.adventure.text.Component

/**
 * The Fabric platform.
 */
class FabricPlatform : NMSPlatform() {

    override fun init() {
        super.init()
        conversionService.registerConverterForNewImplType(Component::class.java, ComponentConverter())
        LOGGER.info("Fabric platform has been initialized.")
    }
}
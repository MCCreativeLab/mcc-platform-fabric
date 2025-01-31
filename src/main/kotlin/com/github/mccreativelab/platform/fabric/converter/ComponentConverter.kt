package com.github.mccreativelab.platform.fabric.converter

import de.verdox.mccreativelab.conversion.converter.MCCConverter
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Component.Serializer
import net.silkmc.silk.core.Silk
import net.kyori.adventure.text.Component as AdventureComponent
import net.minecraft.network.chat.Component as NativeComponent

/**
 * A converter for components.
 */
class ComponentConverter: MCCConverter<NativeComponent, AdventureComponent> {

    /**
     * Wraps a native component.
     * @param nativeType The native component.
     * @return The wrapped component.
     */
    override fun wrap(nativeType: Component?): MCCConverter.ConversionResult<net.kyori.adventure.text.Component> {
        val json = Serializer.toJson(nativeType ?: return notDone(null), Silk.server!!.registryAccess())
        return done(GsonComponentSerializer.gson().deserialize(json))
    }

    /**
     * Unwraps a platform implementation component.
     * @param platformImplType The platform implementation component.
     * @return The unwrapped component.
     */
    override fun unwrap(platformImplType: net.kyori.adventure.text.Component?): MCCConverter.ConversionResult<Component> {
        val json = GsonComponentSerializer.gson().serialize(platformImplType ?: return notDone(null))
        return done(Serializer.fromJson(json, Silk.server!!.registryAccess()))
    }

    /**
     * Gets the class of the platform implementation.
     * @return The class of the platform implementation.
     */
    override fun apiImplementationClass(): Class<net.kyori.adventure.text.Component> {
        return AdventureComponent::class.java
    }

    /**
     * Gets the class of the native Minecraft type.
     * @return The class of the native Minecraft type.
     */
    override fun nativeMinecraftType(): Class<Component> {
        return NativeComponent::class.java
    }
}
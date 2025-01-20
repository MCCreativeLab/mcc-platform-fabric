package com.github.mccreativelab.platform.fabric.converter

import de.verdox.mccreativelab.conversion.converter.MCCConverter
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Component.Serializer
import net.silkmc.silk.core.Silk
import net.kyori.adventure.text.Component as AdventureComponent
import net.minecraft.network.chat.Component as NativeComonent

// TODO: Test this converter
class ComponentConverter: MCCConverter<NativeComonent, AdventureComponent> {
    override fun wrap(nativeType: Component?): MCCConverter.ConversionResult<net.kyori.adventure.text.Component> {
        val json = Serializer.toJson(nativeType ?: return notDone(null), Silk.server!!.registryAccess())
        return done(GsonComponentSerializer.gson().deserialize(json))
    }

    override fun unwrap(platformImplType: net.kyori.adventure.text.Component?): MCCConverter.ConversionResult<Component> {
        val json = GsonComponentSerializer.gson().serialize(platformImplType ?: return notDone(null))
        return done(Serializer.fromJson(json, Silk.server!!.registryAccess()))
    }

    override fun apiImplementationClass(): Class<net.kyori.adventure.text.Component> {
        return AdventureComponent::class.java
    }

    override fun nativeMinecraftType(): Class<Component> {
        return NativeComonent::class.java
    }
}
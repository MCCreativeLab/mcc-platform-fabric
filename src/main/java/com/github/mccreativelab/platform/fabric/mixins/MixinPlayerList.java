package com.github.mccreativelab.platform.fabric.mixins;

import com.google.common.reflect.TypeToken;
import com.llamalad7.mixinextras.sugar.Local;
import de.verdox.mccreativelab.wrapper.entity.MCCEntity;
import de.verdox.mccreativelab.wrapper.event.player.MCCPlayerJoinEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * The mixin class for PlayerList.
 * <p>
 * Implements the following events:
 * - MCCPlayerJoinEvent
 */
@Mixin(PlayerList.class)
public class MixinPlayerList {

    /**
     * Injects the join event for the player.
     *
     * @param connection The connection.
     * @param player     The player.
     * @param cookie     The cookie.
     * @param ci         The callback info.
     * @param mutableComponent The mutable component.
     */
    @Inject(method = "placeNewPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V", shift = At.Shift.AFTER))
    public void injectPlaceNewPlayer(Connection connection, ServerPlayer player, CommonListenerCookie cookie, CallbackInfo ci, @Local MutableComponent mutableComponent) {
        MCCPlayerJoinEvent event = new MCCPlayerJoinEvent(
                MCCPlatform.getInstance().getConversionService().wrap((Entity) player, TypeToken.of(MCCEntity.class)),
                null // TODO: convert mutableComponent to MCCComponent
        );

        event.callEvent(); // MCCPlayerJoinEvent isn't cancellable
    }
}
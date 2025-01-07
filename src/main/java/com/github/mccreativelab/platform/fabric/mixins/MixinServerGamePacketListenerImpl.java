package com.github.mccreativelab.platform.fabric.mixins;

import com.google.common.reflect.TypeToken;
import de.verdox.mccreativelab.wrapper.entity.types.MCCPlayer;
import de.verdox.mccreativelab.wrapper.event.player.MCCPlayerToggleSprintEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * The mixin class for ServerGamePacketListenerImpl.
 * <p>
 * Implements the following events:
 * <p>
 * - MCCPlayerToggleSprintEvent
 */
@Mixin(ServerGamePacketListenerImpl.class)
public class MixinServerGamePacketListenerImpl {

    @Shadow public ServerPlayer player;

    /**
     * Injects the sprinting event for the player.
     *
     * @param packet The packet.
     * @param ci     The callback info.
     */
    @Inject(at = @At(value = "TAIL"), method = "handlePlayerCommand", cancellable = true) // TODO: find better injection point
    private void injectHandlePlayerCommand(ServerboundPlayerCommandPacket packet, CallbackInfo ci) {
        if (packet.getAction() == ServerboundPlayerCommandPacket.Action.STOP_SPRINTING) {
            MCCPlayerToggleSprintEvent event = new MCCPlayerToggleSprintEvent(
                    MCCPlatform.getInstance().getConversionService().wrap(player, TypeToken.of(MCCPlayer.class)),
                    packet.getAction() == ServerboundPlayerCommandPacket.Action.START_SPRINTING,
                    ci.isCancelled() // TODO: check if this is correct
            );

            boolean cancelled = event.callEvent();

            if (cancelled) {
                player.setSprinting(false); // check if this is correct
                ci.cancel();
            }
        }
    }
}
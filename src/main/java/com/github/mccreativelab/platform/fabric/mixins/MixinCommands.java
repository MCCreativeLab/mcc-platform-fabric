package com.github.mccreativelab.platform.fabric.mixins;

import com.google.common.reflect.TypeToken;
import de.verdox.mccreativelab.wrapper.entity.types.MCCPlayer;
import de.verdox.mccreativelab.wrapper.event.player.MCCPlayerCommandSendEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;

@Mixin(Commands.class)
public class MixinCommands {

    @Inject(at = @At("TAIL"), method = "sendCommands")
    private void injectRunSync(ServerPlayer player, CallbackInfo ci) {
        MCCPlayerCommandSendEvent event = new MCCPlayerCommandSendEvent(
                MCCPlatform.getInstance().getConversionService().wrap(player, TypeToken.of(MCCPlayer.class)),
                Collections.emptyList() // TODO: check if this is correct
        );
        event.callEvent();
    }
}

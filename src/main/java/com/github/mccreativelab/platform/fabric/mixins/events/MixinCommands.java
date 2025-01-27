package com.github.mccreativelab.platform.fabric.mixins.events;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import de.verdox.mccreativelab.wrapper.event.player.MCCPlayerCommandSendEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Commands.class)
public class MixinCommands {

    @Inject(at = @At(value = "TAIL"), method = "sendCommands")
    public void injectSendCommands(ServerPlayer player, CallbackInfo ci, @Local RootCommandNode<SharedSuggestionProvider> rootCommandNode) {
        MCCPlayerCommandSendEvent event = new MCCPlayerCommandSendEvent(
                MCCPlatform.getInstance().getConversionService().wrap((Entity) player),
                rootCommandNode.getChildren().stream().map(CommandNode::getName).toList()
        );

        event.callEvent(); // not cancellable
    }
}

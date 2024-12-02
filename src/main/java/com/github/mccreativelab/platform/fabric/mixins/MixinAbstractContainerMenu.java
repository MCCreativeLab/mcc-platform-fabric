package com.github.mccreativelab.platform.fabric.mixins;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for AbstractContainerMenu
 */
@Mixin(AbstractContainerMenu.class)
public class MixinAbstractContainerMenu {

    /**
     * Injected method for inventory click
     * @param i the slot
     * @param j the button
     * @param clickType the click type
     * @param player the player
     * @param ci the callback info
     */
    @Inject(method = "doClick", at = @At("HEAD"))
    private void injectDoClick(int i, int j, ClickType clickType, Player player, CallbackInfo ci) {
        // TODO: call MCCInventoryClickEvent
    }
}

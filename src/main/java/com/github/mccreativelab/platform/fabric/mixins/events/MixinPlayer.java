package com.github.mccreativelab.platform.fabric.mixins.events;

import de.verdox.mccreativelab.wrapper.event.player.MCCPlayerDropItemEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class MixinPlayer {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;setPickUpDelay(I)V"), method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", cancellable = true)
    public void injectDrop(ItemStack droppedItem, boolean dropAround, boolean includeThrowerName, CallbackInfoReturnable<ItemEntity> cir) {
        Player player = (Player) (Object) this;
        MCCPlayerDropItemEvent event = new MCCPlayerDropItemEvent(
                MCCPlatform.getInstance().getConversionService().wrap(player),
                MCCPlatform.getInstance().getConversionService().wrap(droppedItem.getEntityRepresentation()),
                false // TODO: check if this is correct
        );
        if (event.callEvent()) {
            cir.setReturnValue(null);
            // TODO: prevent item from being dropped/removed
        }
    }
}

package com.github.mccreativelab.platform.fabric.mixins;

import com.google.common.reflect.TypeToken;
import de.verdox.mccreativelab.wrapper.entity.MCCEntity;
import de.verdox.mccreativelab.wrapper.entity.types.MCCPlayer;
import de.verdox.mccreativelab.wrapper.event.player.MCCPlayerDropItemEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * The mixin class for Player.
 * <p>
 * Implements the following events:
 * - MCCPlayerDropItemEvent
 */
@Mixin(Player.class)
public class MixinPlayer {

    /**
     * Injects the drop event for the player.
     *
     * @param itemStack The item stack to drop.
     * @param bl        Whether the item stack should be dropped.
     * @param cir       The callback info returnable.
     */
    @Inject(at = @At("TAIL"), method = "drop(Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/item/ItemEntity;", cancellable = true)
    public void injectDrop(ItemStack itemStack, boolean bl, CallbackInfoReturnable<ItemEntity> cir) {
        Player player = (Player) (Object) this;

        MCCPlayerDropItemEvent event = new MCCPlayerDropItemEvent(
                MCCPlatform.getInstance().getConversionService().wrap(player, TypeToken.of(MCCPlayer.class)),
                MCCPlatform.getInstance().getConversionService().wrap((Entity) cir.getReturnValue(), TypeToken.of(MCCEntity.class)),
                cir.isCancelled() // TODO: check if this is correct
        );

        if (event.callEvent()) {
            cir.setReturnValue(null);
        }
    }
}

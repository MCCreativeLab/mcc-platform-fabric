package com.github.mccreativelab.platform.fabric.mixins;

import com.google.common.reflect.TypeToken;
import com.llamalad7.mixinextras.sugar.Local;
import de.verdox.mccreativelab.wrapper.entity.MCCEntity;
import de.verdox.mccreativelab.wrapper.event.entity.MCCSlimeSplitEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * The mixin class for Slime.
 * <p>
 * Implements the following events:
 * <p>
 * - {@link MCCSlimeSplitEvent}
 */
@Mixin(Slime.class)
public class MixinSlime {

    /**
     * Injects the split event for the slime.
     *
     * @param reason The reason for the removal.
     * @param ci     The callback info.
     */
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I", shift = At.Shift.AFTER), method = "remove", cancellable = true)
    public void injectRemove(Entity.RemovalReason reason, CallbackInfo ci, @Local(ordinal = 1) int k) {
        MCCSlimeSplitEvent event = new MCCSlimeSplitEvent(
                MCCPlatform.getInstance().getConversionService().wrap((Slime) (Object) this, TypeToken.of(MCCEntity.class)),
                ci.isCancelled(), // TODO: check if this is correct
                k // TODO: check if this is correct
        );

        if (event.callEvent()) {
            ci.cancel();
        }
    }
}

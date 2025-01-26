package com.github.mccreativelab.platform.fabric.mixins.events;

import com.google.common.reflect.TypeToken;
import com.llamalad7.mixinextras.sugar.Local;
import de.verdox.mccreativelab.wrapper.entity.MCCEntity;
import de.verdox.mccreativelab.wrapper.event.entity.MCCSlimeSplitEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slime.class)
public abstract class MixinSlime extends Mob {

    protected MixinSlime(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow public abstract void remove(Entity.RemovalReason reason);

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I", shift = At.Shift.AFTER), method = "remove", cancellable = true)
    public void injectRemove(Entity.RemovalReason reason, CallbackInfo ci, @Local(ordinal = 1) int k) {
        MCCSlimeSplitEvent event = new MCCSlimeSplitEvent(
                MCCPlatform.getInstance().getConversionService().wrap((Slime) (Object) this, TypeToken.of(MCCEntity.class)),
                false, // TODO: check if this is correct
                k // TODO: check if this is correct
        ); // TODO: bug: is always cancelled

        if (event.callEvent()) {
            super.remove(reason);
            ci.cancel();
        }
    }
}

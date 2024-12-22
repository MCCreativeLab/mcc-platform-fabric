package com.github.mccreativelab.platform.fabric.mixins;

import com.google.common.reflect.TypeToken;
import de.verdox.mccreativelab.wrapper.entity.MCCEntity;
import de.verdox.mccreativelab.wrapper.event.vehicle.MCCVehicleDamageEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * The mixin class for VehicleEntity.
 * <p>
 * Implements the following events:
 * - MCCVehicleDamageEvent
 */
@Mixin(VehicleEntity.class)
public class MixinVehicleEntity {

    /**
     * Injects the damage event for the vehicle.
     *
     * @param source The source of the damage.
     * @param amount The amount of damage.
     * @param cir    The callback info returnable.
     */
    @Inject(at = @At("HEAD"), method = "hurt")
    public void injectHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        // MCCEntity vehicle, MCCEntity attacker, double damage, boolean cancelled
        MCCVehicleDamageEvent event = new MCCVehicleDamageEvent(
                MCCPlatform.getInstance().getConversionService().wrap((VehicleEntity) (Object) this, TypeToken.of(MCCEntity.class)),
                MCCPlatform.getInstance().getConversionService().wrap((VehicleEntity) (Object) source.getEntity(), TypeToken.of(MCCEntity.class)), // TODO: check if this is correct - because there is also getDirectEntity()
                amount,
                cir.isCancelled()
        );

        if (event.callEvent()) {
            cir.cancel(); // TODO: check if this is correct
        }
    }
}

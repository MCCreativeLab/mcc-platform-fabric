package com.github.mccreativelab.platform.fabric.mixins;

import com.google.common.reflect.TypeToken;
import de.verdox.mccreativelab.wrapper.event.world.MCCWorldLoadEvent;
import de.verdox.mccreativelab.wrapper.event.world.MCCWorldUnloadEvent;
import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import de.verdox.mccreativelab.wrapper.world.MCCWorld;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * The mixin class for Level.
 * <p>
 * Implements the following events:
 * - MCCWorldUnloadEvent
 */
@Mixin(ServerLevel.class)
public class MixinServerLevel {

    /**
     * Injects the load event for the world.
     *
     * @param ci The callback info.
     */
    @Inject(at = @At("TAIL"), method = "<init>")
    public void injectInit(CallbackInfo ci) {
        MCCWorldLoadEvent event = new MCCWorldLoadEvent(
                MCCPlatform.getInstance().getConversionService().wrap((Level) (Object) this, TypeToken.of(MCCWorld.class)) // TODO: check if this is correct
        );

        event.callEvent(); // MCCWorldLoadEvent isn't cancellable
    }

    /**
     * Injects the unload event for the world.
     *
     * @param ci The callback info.
     */
    @Inject(at = @At("HEAD"), method = "close", cancellable = true)
    public void injectClose(CallbackInfo ci) {
        MCCWorldUnloadEvent event = new MCCWorldUnloadEvent(
                MCCPlatform.getInstance().getConversionService().wrap((Level) (Object) this, TypeToken.of(MCCWorld.class)), // TODO: check if this is correct
                ci.isCancelled() // TODO: check if this is correct
        );

        if (event.callEvent()) {
            ci.cancel();
        }
    }
}

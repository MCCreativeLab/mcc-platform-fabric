package com.github.mccreativelab.platform.fabric.mixins.lifecycle;

import de.verdox.mccreativelab.wrapper.platform.MCCPlatform;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    @Inject(at = @At("HEAD"), method = "loadLevel")
    public void injectHeadLoadLevel(CallbackInfo ci) {
        MCCPlatform.getInstance().triggerLifecycleEvent(MCCPlatform.Lifecycle.BEFORE_WORLD_LOAD);
        System.out.println("BEFORE_WORLD_LOAD");
    }

    @Inject(at = @At("TAIL"), method = "loadLevel")
    public void injectTailLoadLevel(CallbackInfo ci) {
        MCCPlatform.getInstance().triggerLifecycleEvent(MCCPlatform.Lifecycle.AFTER_WORLD_LOAD);
        System.out.println("AFTER_WORLD_LOAD");
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;buildServerStatus()Lnet/minecraft/network/protocol/status/ServerStatus;", shift = At.Shift.AFTER), method = "runServer")
    public void injectSpin(CallbackInfo ci) {
        MCCPlatform.getInstance().triggerLifecycleEvent(MCCPlatform.Lifecycle.SERVER_STARTUP_COMPLETE);
        System.out.println("SERVER_STARTUP_COMPLETE");
    }
}

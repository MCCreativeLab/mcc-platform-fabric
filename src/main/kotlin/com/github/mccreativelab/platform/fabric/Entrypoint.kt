package com.github.mccreativelab.platform.fabric

import de.verdox.mccreativelab.wrapper.platform.MCCPlatform
import java.util.logging.Logger

/**
 * The logger for the mod.
 */
val logger = Logger.getLogger("mcc-platform-fabric")

/**
 * The main entrypoint for the mod.
 */
fun initMain() = Unit

/**
 * The entrypoint for the server.
 */
fun initServer() {
    MCCPlatform.INSTANCE.setup(FabricPlatform()) {
        it.init()
        logger.info("MCCPlatform has been setup.")
    }
}

// TODO: MCCVehicleMoveEvent
// TODO: MCCVehicleExitEvent
// TODO: MCCVehicleUpdateEvent
// TODO: MCCVehicleCreateEvent
// TODO: MCCVehicleDestroyEvent
// TODO: MCCVehicleEvent
// TODO: MCCVehicleEnterEvent
// TODO: MCCVehicleCollisionEvent
// TODO: MCCVehicleEntityCollisionEvent
// TODO: MCCItemSpawnEvent
// TODO: MCCItemMergeEvent
// TODO: MCCEntityEnterLoveModeEvent
// TODO: MCCEntityPlaceEvent
// TODO: MCCSheepRegrowWoolEvent
// TODO: MCCEntityMountEvent
// TODO: MCCEntityAirChangeEvent
// TODO: MCCFireworkExplodeEvent
// TODO: MCCEntityDropItemEvent
// TODO: MCCEntityToggleGlideEvent
// TODO: MCCItemDespawnEvent
// TODO: MCCPigZombieAngerEvent
// TODO: MCCProjectileLaunchEvent
// TODO: MCCEntityBreakDoorEvent
// TODO: MCCEntityDismountEvent
// TODO: MCCEntityPickupItemEvent
// TODO: MCCEntityInteractEvent
// TODO: MCCProjectileHitEvent
// TODO: MCCEntityEvent
// TODO: MCCEntityTeleportEvent
// TODO: MCCEntityCombustEvent
// TODO: MCCEntityCombustByEntityEvent
// TODO: MCCEntityChangeBlockEvent
// TODO: MCCEntityToggleSwimEvent
// TODO: MCCPotionSplashEvent
// TODO: MCCEntitySpawnEvent
// TODO: MCCAreaEffectCloudApplyEvent
// TODO: MCCPlayerLeashEntityEvent
// TODO: MCCEntityResurrectEvent
// TODO: MCCExplosionPrimeEvent
// TODO: MCCArrowBodyCountChangeEvent
// TODO: MCCEntityCombustByBlockEvent
// TODO: MCCBatToggleSleepEvent
// TODO: MCCStriderTemperatureChangeEvent
// TODO: MCCHorseJumpEvent
// TODO: MCCExpBottleEvent
// TODO: MCCEntityEnterBlockEvent
// TODO: MCCWorldEvent
// TODO: MCCSpawnChangeEvent
// TODO: MCCWorldInitEvent
// TODO: MCCServerListPingEvent
// TODO: MCCServerEvent
// TODO: MCCFurnaceExtractEvent
// TODO: MCCWeatherEvent
// TODO: MCCHangingEvent
// TODO: MCCBlockPistonExtendEvent
// TODO: MCCBlockPistonEvent
// TODO: MCCBlockFromToEvent
// TODO: MCCBellResonateEvent
// TODO: MCCLeavesDecayEvent
// TODO: MCCBlockExpEvent
// TODO: MCCBlockBurnEvent
// TODO: MCCBlockPistonRetractEvent
// TODO: MCCBellRingEvent
// TODO: MCCBlockRedstoneEvent
// TODO: MCCFluidLevelChangeEvent
// TODO: MCCBlockCanBuildEvent
// TODO: MCCBlockPhysicsEvent
// TODO: MCCSculkBloomEvent
// TODO: MCCBlockBreakEvent
// TODO: MCCBlockEvent
// TODO: MCCPlayerChannelEvent
// TODO: MCCPlayerMoveEvent
// TODO: MCCPlayerInteractEntityEvent
// TODO: MCCPlayerCommandPreprocessEvent
// TODO: MCCPlayerShowEntityEvent
// TODO: MCCPlayerToggleSneakEvent
// TODO: MCCPlayerHideEntityEvent
// TODO: MCCPlayerToggleFlightEvent
// TODO: MCCPlayerLocaleChangeEvent
// TODO: MCCPlayerLevelChangeEvent
// TODO: MCCPlayerRegisterChannelEvent
// TODO: MCCPlayerBedLeaveEvent
// TODO: MCCPlayerEvent
// TODO: MCCPlayerEggThrowEvent
// TODO: MCCPlayerItemHeldEvent
// TODO: MCCPlayerChangedWorldEvent
// TODO: MCCPlayerAttemptPickupItemEvent
// TODO: MCCPlayerUnregisterChannelEvent
// TODO: MCCPlayerExpChangeEvent

/*
 * needs testing
 * MCCPlayerJoinEvent
 * MCCPlayerDropItemEvent
 * MCCWorldLoadEvent
 * MCCWorldUnloadEvent
 * MCCPlayerCommandSendEvent
 * MCCPlayerToggleSprintEvent
 * MCCVehicleDamageEvent
 * MCCWorldSaveEvent
 * MCCSlimeSplitEvent
 */
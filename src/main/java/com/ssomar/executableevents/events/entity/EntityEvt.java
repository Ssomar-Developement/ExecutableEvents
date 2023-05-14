package com.ssomar.executableevents.events.entity;

import com.ssomar.score.SsomarDev;
import com.ssomar.sevents.events.player.kill.entity.PlayerKillEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class EntityEvt implements Listener {


    /*
     * @EventHandler public void
     * onAreaEffectCloudApplyEvent(AreaEffectCloudApplyEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void
     * onArrowBodyCountChangeEvent(ArrowBodyCountChangeEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * /*@EventHandler public void onBatToggleSleepEvent(BatToggleSleepEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    /*
     * @EventHandler public void onCreatureSpawnEvent(CreatureSpawnEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    /*
     * @EventHandler public void onCreeperPowerEvent(CreeperPowerEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void
     * onEnderDragonChangePhaseEvent(EnderDragonChangePhaseEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * /*@EventHandler public void onEntityAirChangeEvent(EntityAirChangeEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    //	@EventHandler
    //	public void onEntityBreakDoorEvent(EntityBreakDoorEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityBreedEvent(EntityBreedEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityChangeBlockEvent(EntityChangeBlockEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityCombustEvent(EntityCombustEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}


    // Don't decomment this event, because it duplicate the
    // EntityDamageByEntityEvent ^ ^


    //	@EventHandler
    //	public void onEntityDropItemEvent(EntityDropItemEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityEnterBlockEvent(EntityEnterBlockEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityEnterLoveModeEvent(EntityEnterLoveModeEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityExplodeEvent(EntityExplodeEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityInteractEvent(EntityInteractEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //	}

    //	@EventHandler
    //	public void onEntityPickupItemEvent(EntityPickupItemEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    /*
     * @EventHandler public void onEntityPortalEnterEvent(EntityPortalEnterEvent e)
     * {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    //	@EventHandler
    //	public void onEntityPortalEvent(EntityPortalEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onEntityPortalExitEvent(EntityPortalExitEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onEntityPoseChangeEvent(EntityPoseChangeEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onEntityPotionEffectEvent(EntityPotionEffectEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onEntityRegainHealthEvent(EntityRegainHealthEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onEntityResurrectEvent(EntityResurrectEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    /*
     * @EventHandler public void onEntitySpawnEvent(EntitySpawnEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    /*
     * @EventHandler public void onEntityTameEvent(EntityTameEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void onEntityTargetEvent(EntityTargetEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * /*@EventHandler public void
     * onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    /*
     * @EventHandler public void onEntityTeleportEvent(EntityTeleportEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * /*@EventHandler public void onEntityToggleGlideEvent(EntityToggleGlideEvent
     * e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void onEntityToggleSwimEvent(EntityToggleSwimEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void onEntityTransformEvent(EntityTransformEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    //	@EventHandler
    //	public void onEntityUnleashEvent(EntityUnleashEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onExpBottleEvent(ExpBottleEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onExplosionPrimeEvent(ExplosionPrimeEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onFireworkExplodeEvent(FireworkExplodeEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onHorseJumpEvent(HorseJumpEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    /*
     * @EventHandler public void onItemDespawnEvent(ItemDespawnEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void onItemMergeEvent(ItemMergeEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void onItemSpawnEvent(ItemSpawnEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    //	@EventHandler
    //	public void onLingeringPotionSplashEvent(LingeringPotionSplashEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onPigZapEvent(PigZapEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onPigZombieAngerEvent(PigZombieAngerEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //	@EventHandler
    //	public void onPlayerLeashEntityEvent(PlayerLeashEntityEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onPotionSplashEvent(PotionSplashEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //
    //	@EventHandler
    //	public void onSheepDyeWoolEvent(SheepDyeWoolEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onSlimeSplitEvent(SlimeSplitEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 " + this.toString() + " " + e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    /*
     * @EventHandler public void onSpawnerSpawnEvent(SpawnerSpawnEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */

    /*
     * @EventHandler public void
     * onStriderTemperatureChangeEvent(StriderTemperatureChangeEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void
     * onVillagerAcquireTradeEvent(VillagerAcquireTradeEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void
     * onVillagerCareerChangeEvent(VillagerCareerChangeEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     *
     * @EventHandler public void
     * onVillagerReplenishTradeEvent(VillagerReplenishTradeEvent e) {
     *
     * SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
     *
     * EventsManager.getInstance().manage(e); }
     */
}

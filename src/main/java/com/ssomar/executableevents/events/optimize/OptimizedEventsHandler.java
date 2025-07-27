package com.ssomar.executableevents.events.optimize;


import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.events.block.custom.*;
import com.ssomar.executableevents.events.entity.custom.*;
import com.ssomar.executableevents.events.haging.HangingBreakByEntityListener;
import com.ssomar.executableevents.events.haging.HangingBreakListener;
import com.ssomar.executableevents.events.haging.HangingPlaceListener;
import com.ssomar.executableevents.events.player.custom.*;
import com.ssomar.executableevents.events.player.itemsadder.ItemsAdderPlayerBlockBreakListener;
import com.ssomar.executableevents.events.player.lands.PlayerEnterLandsEventEI;
import com.ssomar.executableevents.events.player.lands.PlayerLeaveLandsEventEI;
import com.ssomar.executableevents.events.raid.RaidFinishListener;
import com.ssomar.executableevents.events.raid.RaidTriggerListener;
import com.ssomar.executableevents.events.raid.RaidWaveListener;
import com.ssomar.executableevents.events.server.BroadcastMessageListener;
import com.ssomar.executableevents.events.server.MapInitializeListener;
import com.ssomar.executableevents.events.server.PluginDisableListener;
import com.ssomar.executableevents.events.server.PluginEnableListener;
import com.ssomar.executableevents.events.vehicle.VehicleCreateListener;
import com.ssomar.executableevents.events.vehicle.VehicleDamageListener;
import com.ssomar.executableevents.events.vehicle.VehicleDestroyListener;
import com.ssomar.executableevents.events.weather.custom.LightningStrikeListener;
import com.ssomar.executableevents.events.weather.custom.ThunderChangeListener;
import com.ssomar.executableevents.events.weather.custom.WeatherChangeListener;
import com.ssomar.executableevents.events.world.custom.ChunkLoadListener;
import com.ssomar.executableevents.events.world.custom.ChunkUnLoadListener;
import com.ssomar.executableevents.events.world.custom.WorldCycleListener;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.SOption;
import com.ssomar.score.utils.Couple;
import com.ssomar.sevents.EventName;
import com.ssomar.sevents.registration.DynamicRegistration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimizedEventsHandler {

    private static OptimizedEventsHandler instance;

    private Map<Option, Couple<List<EventName>, Listener>> registered;

    public OptimizedEventsHandler() {
        init();
    }

    public static OptimizedEventsHandler getInstance() {
        if (instance == null) instance = new OptimizedEventsHandler();
        return instance;
    }

    public void init() {
        registered = new HashMap<>();
    }

    public void reload() {
        for (Couple<List<EventName>, Listener> couple : registered.values()) {
            if (couple == null) continue;
            if (couple.getElem1() != null) {
                for (EventName eN : couple.getElem1()) {
                    DynamicRegistration.getInstance().unregister(eN, ExecutableEvents.plugin.getPlugin());
                }
            }
            if (couple.getElem2() != null) {
                HandlerList.unregisterAll(couple.getElem2());
            }
        }
        init();
    }

    public void read(SOption option) {
        if(!(option instanceof Option)) return;
        Option o = (Option) option;
        if (!registered.containsKey(option)) {
            List<EventName> eventsName = new ArrayList<>();
            Listener mainListerner = null;
            switch (o) {

                case LIGHTNING_STRIKE:
                    mainListerner = new LightningStrikeListener();
                    break;
                case REDSTONE_BLOCK_ACTIVATION:
                    mainListerner = new BlockRedstoneListener();
                    break;
                case BLOCK_DRY:
                    eventsName.add(EventName.BLOCK_DRY);
                    DynamicRegistration.getInstance().register(EventName.BLOCK_DRY, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new BlockDryListener();
                    break;
                case ITEMSADDER_PLAYER_BLOCK_BREAK:
                    if (SCore.hasItemsAdder) mainListerner = new ItemsAdderPlayerBlockBreakListener();
                    break;
                case CROP_GROW:
                    mainListerner = new CropGrow();
                    break;
                case PLAYER_RIGHT_CLICK:
                    eventsName.add(EventName.PLAYER_RIGHT_CLICK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RIGHT_CLICK_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerRightClickEvent();
                    break;
                case PLAYER_ALL_CLICK:
                    eventsName.add(EventName.PLAYER_RIGHT_CLICK_EVENT);
                    eventsName.add(EventName.PLAYER_LEFT_CLICK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RIGHT_CLICK_EVENT, ExecutableEvents.plugin.getPlugin());
                    DynamicRegistration.getInstance().register(EventName.PLAYER_LEFT_CLICK_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerAllClickEvent();
                    break;
                case PLAYER_BRUSH_BLOCK:
                    mainListerner = new PlayerBrushBlockListener();
                    break;
                case PLAYER_BUCKET_ENTITY:
                    mainListerner = new PlayerBucketEntityListener();
                    break;
                case PLAYER_HARVEST_BLOCK:
                    mainListerner = new PlayerHarvestBlockListener();
                    break;
                case PLAYER_HIDE_ENTITY:
                    //Refers 1.18.2
                    if (SCore.is1v18Plus()) mainListerner = new PlayerHideEntityListener();
                    break;
                case PLAYER_EQUIP_ARMOR:
                    eventsName.add(EventName.PLAYER_EQUIP_ARMOR_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_EQUIP_ARMOR_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerEquipArmorListener();
                    break;
                case PLAYER_UNEQUIP_ARMOR:
                    eventsName.add(EventName.PLAYER_EQUIP_ARMOR_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_EQUIP_ARMOR_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerUnequipArmorListener();
                    break;
                case PLAYER_KICK:
                    mainListerner = new PlayerKickListener();
                    break;
                case PLAYER_LAUNCH_PROJECTILE:
                    mainListerner = new ProjectileLaunchListener();
                    break;
                case PLAYER_OPEN_INVENTORY:
                    mainListerner = new PlayerOpenInventoryListener();
                    break;
                case PLAYER_RECEIVE_EFFECT:
                    mainListerner = new PlayerReceiveEffectListener();
                    break;
                case PLAYER_REGAIN_HEALTH:
                    mainListerner = new PlayerRegainHealthListener();
                    break;
                case PLAYER_RIPTIDE:
                    mainListerner = new PlayerRiptideListener();
                    break;
                case PLAYER_SHOW_ENTITY:
                    //Refers 1.18.2
                    if (SCore.is1v18Plus()) mainListerner = new PlayerShowEntityListener();
                    break;
                case PLAYER_SPAWN_CHANGE:
                    mainListerner = new PlayerSpawnChangeListener();
                    break;
                case PLAYER_SWAP_HAND:
                    mainListerner = new PlayerSwaphandListener();
                    break;
                case PLAYER_LEFT_CLICK:
                    eventsName.add(EventName.PLAYER_LEFT_CLICK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_LEFT_CLICK_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerLeftClickEvent();
                    break;
                case PLAYER_LEVEL_CHANGE:
                    mainListerner = new PlayerLevelChangeListener();
                    break;
                case PLAYER_CONSUME:
                    mainListerner = new PlayerConsumeEvent();
                    break;
                case PLAYER_CUSTOM_LAUNCH:
                    mainListerner = new PlayerCustomLaunchListener();
                    break;
                case PLAYER_DROP_ITEM:
                    mainListerner = new PlayerDropItemEvent();
                    break;
                case PLAYER_CLICK_ON_PLAYER:
                    eventsName.add(EventName.PLAYER_RIGHT_CLICK_ON_PLAYER_EVENT);
                    eventsName.add(EventName.PLAYER_LEFT_CLICK_ON_PLAYER_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RIGHT_CLICK_ON_PLAYER_EVENT, ExecutableEvents.plugin.getPlugin());
                    DynamicRegistration.getInstance().register(EventName.PLAYER_LEFT_CLICK_ON_PLAYER_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerClickOnPlayerEvent();
                    break;
                case PLAYER_ENABLE_FLY:
                    eventsName.add(EventName.PLAYER_ENABLE_FLY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_ENABLE_FLY_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerEnableFlyEvent();
                    break;
                case PLAYER_ENABLE_GLIDE:
                    eventsName.add(EventName.PLAYER_ENABLE_GLIDE_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_ENABLE_GLIDE_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerEnableGlideEvent();
                    break;
                case PLAYER_ENABLE_SNEAK:
                    eventsName.add(EventName.PLAYER_ENABLE_SNEAK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_ENABLE_SNEAK_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerEnableSneakEvent();
                    break;
                case PLAYER_ENABLE_SPRINT:
                    eventsName.add(EventName.PLAYER_ENABLE_SPRINT_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_ENABLE_SPRINT_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerEnableSprintEvent();
                    break;
                case PLAYER_ENTITY_PLACE:
                    mainListerner = new PlayerEntityPlaceListener();
                    break;
                case PLAYER_BED_ENTER:
                    mainListerner = new PlayerBedEnterEvent();
                    break;
                case PLAYER_BED_LEAVE:
                    mainListerner = new PlayerLeaveBedEvent();
                    break;
                case PLAYER_BEFORE_DEATH:
                    eventsName.add(EventName.PLAYER_BEFORE_DEATH_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_BEFORE_DEATH_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerBeforeDeathEvent();
                    break;
                case PLAYER_BLOCK_BREAK:
                    mainListerner = new PlayerBlockBreakEvent();
                    break;
                case PLAYER_BLOCK_PLACE:
                    mainListerner = new PlayerBlockPlaceEvent();
                    break;
                case PLAYER_CHANGE_WORLD:
                    mainListerner = new PlayerChangeWorldEvent();
                    break;
                case PLAYER_CLICK_ON_ENTITY:
                    eventsName.add(EventName.PLAYER_RIGHT_CLICK_ON_ENTITY_EVENT);
                    eventsName.add(EventName.PLAYER_LEFT_CLICK_ON_ENTITY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RIGHT_CLICK_ON_ENTITY_EVENT, ExecutableEvents.plugin.getPlugin());
                    DynamicRegistration.getInstance().register(EventName.PLAYER_LEFT_CLICK_ON_ENTITY_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerClickOnEntityEvent();
                    break;
                case PLAYER_CONNECTION:
                    mainListerner = new PlayerConnectionEvent();
                    break;
                case PLAYER_DISCONNECTION:
                    mainListerner = new PlayerDisconnectionEvent();
                    break;
                case PLAYER_DISMOUNT:
                    mainListerner = new PlayerDismountEvent();
                    break;
                case PLAYER_EXPERIENCE_CHANGE:
                    mainListerner = new PlayerExpChangeListener();
                    break;
                case PLAYER_FOOD_CHANGE:
                    mainListerner = new PlayerFoodChange();
                    break;

                case PLAYER_DEATH:
                    mainListerner = new PlayerDeathEvent();
                    break;
                case PLAYER_DISABLE_FLY:
                    eventsName.add(EventName.PLAYER_DISABLE_FLY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_DISABLE_FLY_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerDisableFlyEvent();
                    break;
                case PLAYER_DISABLE_GLIDE:
                    eventsName.add(EventName.PLAYER_DISABLE_GLIDE_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_DISABLE_GLIDE_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerDisableGlideEvent();
                    break;
                case PLAYER_DISABLE_SNEAK:
                    eventsName.add(EventName.PLAYER_DISABLE_SNEAK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_DISABLE_SNEAK_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerDisableSneakEvent();
                    break;
                case PLAYER_DISABLE_SPRINT:
                    eventsName.add(EventName.PLAYER_DISABLE_SPRINT_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_DISABLE_SPRINT_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerDisableSprintEvent();
                    break;
                case PLAYER_EDIT_BOOK:
                    mainListerner = new PlayerEditBookEvent();
                    break;
                case PLAYER_FERTILIZE_BLOCK:
                    mainListerner = new PlayerFertilizeBlockEvent();
                    break;
                case PLAYER_FILL_BUCKET:
                    mainListerner = new PlayerBucketFillEvent();
                    break;
                case PLAYER_EMPTY_BUCKET:
                    mainListerner = new PlayerBucketEmptyListener();
                    break;
                case PLAYER_FIRST_CONNECTION:
                    mainListerner = new PlayerFirstConnectionEvent();
                    break;
                case PLAYER_FISH_BLOCK:
                    eventsName.add(EventName.PLAYER_FISH_BLOCK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_BLOCK_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerFishBlockEvent();
                    break;
                case PLAYER_FISH_ENTITY:
                    eventsName.add(EventName.PLAYER_FISH_ENTITY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_ENTITY_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerFishEntityEvent();
                    break;
                case PLAYER_FISH_FISH:
                    eventsName.add(EventName.PLAYER_FISH_FISH_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_FISH_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerFishFishEvent();
                    break;
                case PLAYER_FISH_NOTHING:
                    if (!SCore.is1v13Less()) {
                        eventsName.add(EventName.PLAYER_FISH_NOTHING_EVENT);
                        DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_NOTHING_EVENT, ExecutableEvents.plugin.getPlugin());
                        mainListerner = new PlayerFishNothingEvent();
                    }
                    break;
                case PLAYER_FISH_PLAYER:
                    eventsName.add(EventName.PLAYER_FISH_PLAYER_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_PLAYER_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerFishPlayerEvent();
                    break;
                case PLAYER_ITEM_BREAK:
                    mainListerner = new PlayerItemBreakEvent();
                    break;
                case PLAYER_HIT_ENTITY:
                    mainListerner = new PlayerHitEntityEvent();
                    break;
                case PLAYER_HIT_PLAYER:
                    mainListerner = new PlayerHitPlayerEvent();
                    break;
                case PLAYER_INVENTORY_CLICK:
                    mainListerner = new PlayerInventoryClickListener();
                    break;
                case PLAYER_JUMP:
                    eventsName.add(EventName.PLAYER_JUMP_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_JUMP_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerJumpEvent();
                    break;
                case PLAYER_KILL_ENTITY:
                    eventsName.add(EventName.PLAYER_KILL_ENTITY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_KILL_ENTITY_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerKillEntityEvent();
                    break;
                case PLAYER_KILL_PLAYER:
                    eventsName.add(EventName.PLAYER_KILL_PLAYER_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_KILL_PLAYER_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerKillPlayerEvent();
                    break;

                case PLAYER_MOUNT:
                    mainListerner = new PlayerMountEvent();
                    break;

                case PLAYER_PARTICIPATE_KILL_ENTITY:
                    eventsName.add(EventName.PLAYER_PARTICIPATE_KILL_ENTITY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_PARTICIPATE_KILL_ENTITY_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerParticipateKillEntityListener();
                    break;

                case PLAYER_PARTICIPATE_KILL_PLAYER:
                    eventsName.add(EventName.PLAYER_PARTICIPATE_KILL_PLAYER_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_PARTICIPATE_KILL_PLAYER_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerParticipateKillPlayerListener();
                    break;

                case PLAYER_WRITE_COMMAND:
                    mainListerner = new PlayerWriteCommandEvent();
                    break;
                case PLAYER_SEND_MESSAGE:
                    mainListerner = new PlayerSendMessageEvent();
                    break;
                case PLAYER_RECEIVE_HIT_BY_ENTITY:
                    eventsName.add(EventName.PLAYER_RECEIVE_HIT_BY_ENTITY_EVENT);
                    eventsName.add(EventName.PROJECTILE_HIT_PLAYER);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_PLAYER, ExecutableEvents.plugin.getPlugin());
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RECEIVE_HIT_BY_ENTITY_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerReceiveHitByEntityEvent();
                    break;
                case PLAYER_RECEIVE_HIT_BY_PLAYER:
                    eventsName.add(EventName.PLAYER_RECEIVE_HIT_BY_PLAYER_EVENT);
                    eventsName.add(EventName.PROJECTILE_HIT_PLAYER);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_PLAYER, ExecutableEvents.plugin.getPlugin());
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RECEIVE_HIT_BY_PLAYER_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerReceiveHitByPlayerEvent();
                    break;
                case PLAYER_RECEIVE_HIT_GLOBAL:
                    eventsName.add(EventName.PLAYER_RECEIVE_HIT_GLOBAL_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RECEIVE_HIT_GLOBAL_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerReceiveHitGlobalEvent();
                    break;
                case PLAYER_RESPAWN:
                    mainListerner = new PlayerRespawnListener();
                    break;
                case PLAYER_SHEAR_ENTITY:
                    mainListerner = new PlayerShearEntityEvent();
                    break;
                case PLAYER_TARGETED_BY_AN_ENTITY:
                    mainListerner = new PlayerTargetedByAnEntityEvent();
                    break;

                case PLAYER_TRAMPLE_CROP:
                    eventsName.add(EventName.PLAYER_TRAMPLE_CROP_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_TRAMPLE_CROP_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerTrampleCropEvent();
                    break;

                case PLAYER_ENTER_IN_HIS_LAND:
                    if (SCore.hasLands) mainListerner = new PlayerEnterLandsEventEI();
                    break;

                case PLAYER_LEAVE_HIS_LAND:
                    if (SCore.hasLands) mainListerner = new PlayerLeaveLandsEventEI();
                    break;

                case PLAYER_TELEPORT:
                    mainListerner = new PlayerTeleportListener();
                    break;

                case PLAYER_PORTAL:
                    mainListerner = new PlayerPortalListener();
                    break;

                case PLAYER_PICKUP_ITEM:
                    mainListerner = new PlayerPickupItem();
                    break;


                case PLAYER_WALK:
                    mainListerner = new PlayerWalkEvent();
                    break;

                case PLAYER_PROJECTILE_HIT_BLOCK:
                    eventsName.add(EventName.PROJECTILE_HIT_BLOCK);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_BLOCK, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerProjectileHitBlock();
                    break;
                case PLAYER_PROJECTILE_HIT_ENTITY:
                    eventsName.add(EventName.PROJECTILE_HIT_ENTITY);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_ENTITY, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerProjectileHitEntity();
                    break;

                case PLAYER_PROJECTILE_HIT_PLAYER:
                    eventsName.add(EventName.PROJECTILE_HIT_PLAYER);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_PLAYER, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new PlayerProjectileHitPlayer();
                    break;

                case CREEPER_POWER_CHANGE:
                    mainListerner = new CreeperPowerEventListener();
                    break;
                case ENTITY_PROJECTILE_HIT_BLOCK:
                    eventsName.add(EventName.PROJECTILE_HIT_BLOCK);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_BLOCK, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new EntityProjectileHitBlock();
                    break;
                case ENTITY_PROJECTILE_HIT_ENTITY:
                    eventsName.add(EventName.PROJECTILE_HIT_ENTITY);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_ENTITY, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new EntityProjectileHitEntity();
                    break;
                case ENTITY_PROJECTILE_HIT_PLAYER:
                    eventsName.add(EventName.PROJECTILE_HIT_PLAYER);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_PLAYER, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new EntityProjectileHitPlayer();
                    break;
                case ENTITY_PLACE_EVENT:
                    mainListerner = new EntityPlaceEventListener();
                    break;
                case ENTITY_SHOOT_BOW:
                    mainListerner = new EntityShootBowListener();
                    break;
                case ENTITY_SPAWN:
                    mainListerner = new EntitySpawnListener();
                    break;
                case ENTITY_SPAWN_TRIALSPAWNER:
                    mainListerner = new EntitySpawnTrialSpawnerListener();
                    break;
                case ENTITY_BEFORE_DEATH:
                    eventsName.add(EventName.ENTITY_BEFORE_DEATH_EVENT);
                    DynamicRegistration.getInstance().register(EventName.ENTITY_BEFORE_DEATH_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new EntityBeforeDeathEvent();
                    break;

                case ENTITY_BREAK_DOOR:
                    mainListerner = new EntityBreakDoorListener();
                    break;

                case ENTITY_BREED:
                    mainListerner = new EntityBreedListener();
                    break;

                case ENTITY_CHANGE_BLOCK:
                    mainListerner = new EntityChangeBlockListener();
                    break;

                case ENTITY_COMBUST_BY_BLOCK:
                    mainListerner = new EntityCombustByBlockListener();
                    break;

                case ENTITY_COMBUST_BY_ENTITY:
                    mainListerner = new EntityCombustByEntityListener();
                    break;

                case ENTITY_DAMAGE_BY_PLAYER:
                    mainListerner = new EntityDamageByPlayerListener();
                    break;

                case ENTITY_DAMAGE_BY_ENTITY:
                    mainListerner = new EntityDamageByEntityListener();
                    break;

                case ENTITY_DAMAGE_BY_BLOCK:
                    mainListerner = new EntityDamageByBlockListener();
                    break;

                case ENTITY_DEATH:
                    mainListerner = new EntityDeathListener();
                    break;

                case ENTITY_DROP_ITEM:
                    mainListerner = new EntityDropItemListener();
                    break;

                case ENTITY_ENTER_BLOCK:
                    mainListerner = new EntityEnterBlockListener();
                    break;

                case ENTITY_ENTER_LOVE_MODE:
                    mainListerner = new EntityEnterLoveModeListener();
                    break;

                case ENTITY_EXPLODE:
                    mainListerner = new EntityExplodeListener();
                    break;

                case ENTITY_PARTICIPATE_KILL_ENTITY:
                    eventsName.add(EventName.ENTITY_PARTICIPATE_KILL_ENTITY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.ENTITY_PARTICIPATE_KILL_ENTITY_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new EntityParticipateKillEntityListener();
                    break;

                case ENTITY_PARTICIPATE_KILL_PLAYER:
                    eventsName.add(EventName.ENTITY_PARTICIPATE_KILL_PLAYER_EVENT);
                    DynamicRegistration.getInstance().register(EventName.ENTITY_PARTICIPATE_KILL_PLAYER_EVENT, ExecutableEvents.plugin.getPlugin());
                    mainListerner = new EntityParticipateKillPlayerListener();
                    break;

                case ENTITY_PICKUP_ITEM:
                    mainListerner = new EntityPickupItemListener();
                    break;

                case ENTITY_PORTAL_ENTER:
                    mainListerner = new EntityPortalEnterListener();
                    break;

                case ENTITY_PORTAL_EXIT:
                    mainListerner = new EntityPortalExitListener();
                    break;

                case ENTITY_REGAIN_HEALTH:
                    mainListerner = new EntityRegainHealthListener();
                    break;

                case ENTITY_RESURRECT:
                    mainListerner = new EntityResurrectListener();
                    break;

                case ENTITY_TAME_BY_PLAYER:
                    mainListerner = new EntityTameByPlayerListener();
                    break;

                case ENTITY_TAME_BY_ENTITY:
                    mainListerner = new EntityTameByEntityListener();
                    break;

                case ENTITY_TARGET_PLAYER:
                    mainListerner = new EntityTargetPlayerListener();
                    break;

                case ENTITY_TARGET_ENTITY:
                    mainListerner = new EntityTargetEntityListener();
                    break;

                case ENTITY_TELEPORT:
                    mainListerner = new EntityTeleportListener();
                    break;

                case ENTITY_TRANSFORM:
                    mainListerner = new EntityTransformListener();
                    break;

                case PLAYER_ENCHANT_ITEM:
                    mainListerner = new EnchantItemListener();
                    break;

                case WEATHER_CHANGE:
                    mainListerner = new WeatherChangeListener();
                    break;
                case THUNDER_CHANGE:
                    mainListerner = new ThunderChangeListener();
                    break;
                case WORLD_DAY:
                case WORLD_NIGHT:
                    mainListerner = new WorldCycleListener();
                    break;

                case CHUNK_LOAD:
                    mainListerner = new ChunkLoadListener();
                    break;
                case CHUNK_UNLOAD:
                    mainListerner = new ChunkUnLoadListener();
                    break;

                case BROADCAST_MESSAGE:
                    mainListerner = new BroadcastMessageListener();
                    break;
                case MAP_INITIALIZE:
                    mainListerner = new MapInitializeListener();
                    break;
                case PLUGIN_DISABLE:
                    mainListerner = new PluginDisableListener();
                    break;
                case PLUGIN_ENABLE:
                    mainListerner = new PluginEnableListener();
                    break;

                case RAID_TRIGGER:
                    mainListerner = new RaidTriggerListener();
                    break;
                case RAID_WAVE:
                    mainListerner = new RaidWaveListener();
                    break;
                case RAID_FINISH:
                    mainListerner = new RaidFinishListener();
                    break;

                case VEHICLE_CREATE:
                    mainListerner = new VehicleCreateListener();
                    break;
                case VEHICLE_DAMAGE:
                    mainListerner = new VehicleDamageListener();
                    break;
                case VEHICLE_DESTROY:
                    mainListerner = new VehicleDestroyListener();
                    break;

                case HANGING_PLACE:
                    mainListerner = new HangingPlaceListener();
                    break;
                case HANGING_BREAK:
                    mainListerner = new HangingBreakListener();
                    break;
                case HANGING_BREAK_BY_ENTITY:
                    mainListerner = new HangingBreakByEntityListener();
                    break;

                case PLAYER_PICKUP_ARROW:
                    mainListerner = new PlayerPickupArrowListener();
                    break;
                case PLAYER_TAKE_LECTERN_BOOK:
                    mainListerner = new PlayerTakeLecternBookListener();
                    break;
                case PLAYER_ADVANCEMENT:
                    mainListerner = new PlayerAdvancementDoneListener();
                    break;

                case CAMPFIRE_START:
                    mainListerner = new CampfireStartListener();
                    break;
                case BLOCK_IGNITE:
                    mainListerner = new BlockIgniteListener();
                    break;
                case BLOCK_BURN:
                    mainListerner = new BlockBurnListener();
                    break;

                case CRAFTER_CRAFT:
                    mainListerner = new CrafterCraftListener();
                    break;
                case LEAVES_DECAY:
                    mainListerner = new LeavesDecayListener();
                    break;
                case SIGN_CHANGE:
                    mainListerner = new SignChangeListener();
                    break;

                case SPONGE_ABSORB:
                    mainListerner = new SpongeAbsorbListener();
                    break;
                case BLOCK_FERTILIZE:
                    mainListerner = new BlockFertilizeListener();
                    break;
                case BLOCK_EXPLODE:
                    mainListerner = new BlockExplodeListener();
                    break;

                case BELL_RESONATE:
                    mainListerner = new BellResonateListener();
                    break;
                case BELL_RING:
                    mainListerner = new BellRingListener();
                    break;
                case NOTE_PLAY:
                    mainListerner = new NotePlayListener();
                    break;

                case ENDERDRAGON_CHANGE_PHASE:
                    mainListerner = new EnderdragonChangePhaseListener();
                    break;
            }
            if (mainListerner != null) {
                ExecutableEvents.plugin.getPlugin().getServer().getPluginManager().registerEvents(mainListerner, ExecutableEvents.plugin.getPlugin());
                registered.put(o, new Couple<>(eventsName, mainListerner));
            }
        }
    }

    public void displayOptimisation() {
        ExecutableEvents.plugin.getPlugin().getServer().getLogger().info("================ [ExecutableEvents Check Options] ================");
        ExecutableEvents.plugin.getPlugin().getServer().getLogger().info("All options available in ExecutableEvents: ");
        //ExecutableItems.plugin.getServer().getLogger().info("More * = More performance hungry");
        ExecutableEvents.plugin.getPlugin().getServer().getLogger().info("true: an ExecutableEvent has an activator that use it.");
        ExecutableEvents.plugin.getPlugin().getServer().getLogger().info("false: no ExecutableEvent require this event. (better performance)");
        ExecutableEvents.plugin.getPlugin().getServer().getLogger().info("Use only what you need, more false = better performance");
        ExecutableEvents.plugin.getPlugin().getServer().getLogger().info("");
        for (Option o : Option.values()) {
            ExecutableEvents.plugin.getPlugin().getServer().getLogger().info(o + " >> " + registered.containsKey(o));
        }
        ExecutableEvents.plugin.getPlugin().getServer().getLogger().info("================ [ExecutableEvents Check Options] ================");
    }
}

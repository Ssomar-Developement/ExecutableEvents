package com.ssomar.executableevents.events.optimize;


import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.events.block.custom.CropGrow;
import com.ssomar.executableevents.events.entity.custom.*;
import com.ssomar.executableevents.events.player.custom.*;
import com.ssomar.executableevents.events.player.itemsadder.ItemsAdderPlayerBlockBreakListener;
import com.ssomar.executableevents.events.player.lands.PlayerEnterLandsEventEI;
import com.ssomar.executableevents.events.player.lands.PlayerLeaveLandsEventEI;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.utils.Couple;
import com.ssomar.sevents.EventName;
import com.ssomar.sevents.registration.DynamicRegistration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

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
                    DynamicRegistration.getInstance().unregister(eN, ExecutableEvents.plugin);
                }
            }
            if (couple.getElem2() != null) {
                HandlerList.unregisterAll(couple.getElem2());
            }
        }
        init();
    }

    public void read(Option option) {
        if (!registered.containsKey(option)) {
            List<EventName> eventsName = new ArrayList<>();
            Listener mainListerner = null;
            switch (option) {

                case ITEMSADDER_PLAYER_BLOCK_BREAK:
                    if(SCore.hasItemsAdder) mainListerner = new ItemsAdderPlayerBlockBreakListener();
                    break;

                case CROP_GROW:
                    mainListerner = new CropGrow();
                    break;

                case PLAYER_RIGHT_CLICK:
                    eventsName.add(EventName.PLAYER_RIGHT_CLICK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RIGHT_CLICK_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerRightClickEvent();
                    break;
                case PLAYER_ALL_CLICK:
                    eventsName.add(EventName.PLAYER_RIGHT_CLICK_EVENT);
                    eventsName.add(EventName.PLAYER_LEFT_CLICK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RIGHT_CLICK_EVENT, ExecutableEvents.plugin);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_LEFT_CLICK_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerAllClickEvent();
                    break;
                case PLAYER_LEFT_CLICK:
                    eventsName.add(EventName.PLAYER_LEFT_CLICK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_LEFT_CLICK_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerLeftClickEvent();
                    break;
                case PLAYER_CONSUME:
                    mainListerner = new PlayerConsumeEvent();
                    break;
                case PLAYER_DROP_ITEM:
                    mainListerner = new PlayerDropItemEvent();
                    break;
                case PLAYER_CLICK_ON_PLAYER:
                    eventsName.add(EventName.PLAYER_RIGHT_CLICK_ON_PLAYER_EVENT);
                    eventsName.add(EventName.PLAYER_LEFT_CLICK_ON_PLAYER_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RIGHT_CLICK_ON_PLAYER_EVENT, ExecutableEvents.plugin);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_LEFT_CLICK_ON_PLAYER_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerClickOnPlayerEvent();
                    break;
                case PLAYER_ACTIVE_FLY:
                    eventsName.add(EventName.PLAYER_ACTIVE_FLY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_ACTIVE_FLY_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerActiveFlyEvent();
                    break;
                case PLAYER_ACTIVE_SNEAK:
                    eventsName.add(EventName.PLAYER_ACTIVE_SNEAK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_ACTIVE_SNEAK_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerActiveSneakEvent();
                    break;
                case PLAYER_ACTIVE_SPRINT:
                    eventsName.add(EventName.PLAYER_ACTIVE_SPRINT_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_ACTIVE_SPRINT_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerActiveSprintEvent();
                    break;
                case PLAYER_BED_ENTER:
                    mainListerner = new PlayerBedEnterEvent();
                    break;
                case PLAYER_BED_LEAVE:
                    mainListerner = new PlayerLeaveBedEvent();
                    break;
                case PLAYER_BEFORE_DEATH:
                    eventsName.add(EventName.PLAYER_BEFORE_DEATH_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_BEFORE_DEATH_EVENT, ExecutableEvents.plugin);
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
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RIGHT_CLICK_ON_ENTITY_EVENT, ExecutableEvents.plugin);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_LEFT_CLICK_ON_ENTITY_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerClickOnEntityEvent();
                    break;
                case PLAYER_CONNECTION:
                    mainListerner = new PlayerConnectionEvent();
                    break;
                case PLAYER_DISCONNECTION:
                    mainListerner = new PlayerDeconnectionEvent();
                    break;
                case PLAYER_DISMOUNT:
                    mainListerner = new PlayerDismountEvent();
                    break;
                case PLAYER_DEATH:
                    mainListerner = new PlayerDeathEvent();
                    break;
                case PLAYER_DISABLE_SNEAK:
                    eventsName.add(EventName.PLAYER_DESACTIVE_SNEAK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_DESACTIVE_SNEAK_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerDesactiveSneakEvent();
                    break;
                case PLAYER_DISABLE_SPRINT:
                    eventsName.add(EventName.PLAYER_DESACTIVE_SPRINT_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_DESACTIVE_SPRINT_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerDesactiveSprintEvent();
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
                case PLAYER_FIRST_CONNECTION:
                    mainListerner = new PlayerFirstConnectionEvent();
                    break;
                case PLAYER_FISH_BLOCK:
                    eventsName.add(EventName.PLAYER_FISH_BLOCK_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_BLOCK_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerFishBlockEvent();
                    break;
                case PLAYER_FISH_ENTITY:
                    eventsName.add(EventName.PLAYER_FISH_ENTITY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_ENTITY_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerFishEntityEvent();
                    break;
                case PLAYER_FISH_FISH:
                    eventsName.add(EventName.PLAYER_FISH_FISH_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_FISH_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerFishFishEvent();
                    break;
                case PLAYER_FISH_NOTHING:
                    if (!SCore.is1v13Less()) {
                        eventsName.add(EventName.PLAYER_FISH_NOTHING_EVENT);
                        DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_NOTHING_EVENT, ExecutableEvents.plugin);
                        mainListerner = new PlayerFishNothingEvent();
                    }
                    break;
                case PLAYER_FISH_PLAYER:
                    eventsName.add(EventName.PLAYER_FISH_PLAYER_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_FISH_PLAYER_EVENT, ExecutableEvents.plugin);
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
                case PLAYER_JUMP:
                    eventsName.add(EventName.PLAYER_JUMP_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_JUMP_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerJumpEvent();
                    break;
                case PLAYER_KILL_ENTITY:
                    eventsName.add(EventName.PLAYER_KILL_ENTITY_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_KILL_ENTITY_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerKillEntityEvent();
                    break;
                case PLAYER_KILL_PLAYER:
                    eventsName.add(EventName.PLAYER_KILL_PLAYER_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_KILL_PLAYER_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerKillPlayerEvent();
                    break;

                case PLAYER_MOUNT:
                    mainListerner = new PlayerMountEvent();
                    break;

                case PLAYER_WRITE_COMMAND:
                    mainListerner = new PlayerWriteCommandEvent();
                    break;
                case PLAYER_RECEIVE_HIT_BY_ENTITY:
                    eventsName.add(EventName.PLAYER_RECEIVE_HIT_BY_ENTITY_EVENT);
                    eventsName.add(EventName.PROJECTILE_HIT_PLAYER);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_PLAYER, ExecutableEvents.plugin);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RECEIVE_HIT_BY_ENTITY_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerReceiveHitByEntityEvent();
                    break;
                case PLAYER_RECEIVE_HIT_BY_PLAYER:
                    eventsName.add(EventName.PLAYER_RECEIVE_HIT_BY_PLAYER_EVENT);
                    eventsName.add(EventName.PROJECTILE_HIT_PLAYER);
                    DynamicRegistration.getInstance().register(EventName.PROJECTILE_HIT_PLAYER, ExecutableEvents.plugin);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RECEIVE_HIT_BY_PLAYER_EVENT, ExecutableEvents.plugin);
                    mainListerner = new PlayerReceiveHitByPlayerEvent();
                    break;
                case PLAYER_RECEIVE_HIT_GLOBAL:
                    eventsName.add(EventName.PLAYER_RECEIVE_HIT_GLOBAL_EVENT);
                    DynamicRegistration.getInstance().register(EventName.PLAYER_RECEIVE_HIT_GLOBAL_EVENT, ExecutableEvents.plugin);
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
                    DynamicRegistration.getInstance().register(EventName.PLAYER_TRAMPLE_CROP_EVENT, ExecutableEvents.plugin);
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


                case PLAYER_WALK:
                    mainListerner = new PlayerWalkEvent();
                    break;

                case LOOP:
                    /* Not registered here
                     * But add it to registered for the checkevents command */
                    registered.put(option, null);
                    break;

                case ENTITY_SPAWN:
                    mainListerner = new EntitySpawnListener();
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

                case ENDERDRAGON_CHANGE_PHASE:
                    mainListerner = new EnderdragonChangePhaseListener();
                    break;

            }
            if (mainListerner != null) {
                ExecutableEvents.plugin.getServer().getPluginManager().registerEvents(mainListerner, ExecutableEvents.plugin);
                registered.put(option, new Couple<>(eventsName, mainListerner));
            }
        }
    }

    public void displayOptimisation() {
        ExecutableEvents.plugin.getServer().getLogger().info("================ [ExecutableEvents Check Options] ================");
        ExecutableEvents.plugin.getServer().getLogger().info("All options available in ExecutableEvents: ");
        //ExecutableItems.plugin.getServer().getLogger().info("More * = More performance hungry");
        ExecutableEvents.plugin.getServer().getLogger().info("true: an ExecutableEvent has an activator that use it.");
        ExecutableEvents.plugin.getServer().getLogger().info("false: no ExecutableEvent require this event. (better performance)");
        ExecutableEvents.plugin.getServer().getLogger().info("Use only what you need, more false = better performance");
        ExecutableEvents.plugin.getServer().getLogger().info("");
        for (Option o : Option.values()) {
            ExecutableEvents.plugin.getServer().getLogger().info(o + " >> " + registered.containsKey(o));
        }
        ExecutableEvents.plugin.getServer().getLogger().info("================ [ExecutableEvents Check Options] ================");
    }
}
